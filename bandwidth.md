# Rede


![](images/Captura%20de%20tela%20de%202019-12-15%2016-18-42.png)


Como falado anteriormente, não é ideal que exista esse cenário. Como foi abordado em sala de aula, não é aconselhado várias requisições, mesmo que sejam de pequeno tamanho, mas sim uma requisição que solicite quando possível. Como a ideia do aplicativo é ter uma playlist diferente pra cada sala, então não teria como prever cada playlist e já salvá-las em um arquivo, tornando-se nossa única opção.

Esse é o código que requisitamos a playlist do usuário. Uma vez com o usuário autenticado, fazemos uma requisição usando *queue* do *Volley* colocando uma requisição do tipo GET na fila. Na resposta, é lido um jsonArray e parseado cada track da playlist. Então, uma vez tendo sucesso, é colocado o token ao lado do *Bearer* para finalizar a requisição. Por fim, adiciona-se à fila de requisições e o volley abstrai a operação jogando o resultado na *thread ui*. 
```kotlin
fun getRecentlyPlayedTracks(callBack: VolleyCallBack): ArrayList<Song> {
        val endpoint = "https://api.spotify.com/v1/playlists/37i9dQZF1EtmslFZGwD6iR/tracks"
        val jsonObjectRequest: JsonObjectRequest =
            object : JsonObjectRequest(
                Request.Method.GET, endpoint, null,
                Response.Listener { response: JSONObject ->
                    val gson = Gson()
                    val jsonArray = response.optJSONArray("items")
                    for (n in 0 until jsonArray.length()) {
                        try {
                            var `object` = jsonArray.getJSONObject(n)
                            `object` = `object`.optJSONObject("track")
                            val song: Song = gson.fromJson(`object`.toString(), Song::class.java)
                            songs.add(song)
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                    callBack.onSuccess()
                },
                Response.ErrorListener { error: VolleyError? -> }
            ) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers: MutableMap<String, String> =
                        HashMap()
                    val token = sharedPreferences!!.getString(
                        Constants.SPOTIFY_TOKEN,
                        Constants.NO_TOKEN
                    )
                    val auth = "Bearer $token"
                    headers["Authorization"] = auth
                    return headers
                }
            }
        queue!!.add(jsonObjectRequest)
        return songs
    }
```

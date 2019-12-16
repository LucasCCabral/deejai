**Energia**


![](images/Captura%20de%20tela%20de%202019-12-15%2016-21-49.png)


O uso de energia certamente é maior no começo do aplicativo, quando precisamos da autenticação do spotify. Depois, têm-se um uso moderado quando foi criado várias salas e também quando foi feita as requisições das playlist. Sabemos que para cada requisição feita pelo _Volley_, precisa-se ligar uma parte específica do hardware do android, fazendo com que o consumo de energia seja intermitente. O uso do gps também provocou um pico, mas é somente usado uma vez e guardado sua localização em _sharedprefs_.


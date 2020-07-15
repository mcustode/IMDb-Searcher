<b>IMDb-Searcher</b>

Projeto de teste para fazer a pesquisa dos títulos da IMBd
Compilado para "jdk-14.0.2"

<b>Criação do jar</b>

Na pasta do projeto executar:

./gradle clean
./gradle fatjar

Sera gerado o arquivo "IMDbSearcher-all.jar" na pasta "build\lib" Este arquivo já terá todas as bibliotecas necessárias para a execução


<b>Instalação</b>

Criar a pasta "db" dentro da pasta onde está o arquivo  IMDbSearcher-all.jar
Copiar o archivo de banco de dados "imdb.db" na pasta "db"

<b>Uso</b>

java -jar IMDbSearcher-all.jar [porta IP]


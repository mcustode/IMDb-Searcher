<h1>IMDb-Searcher</h1>

Projeto de teste para fazer a pesquisa dos títulos da IMBd
Compilado para "jdk-14.0.2"

<p>Este serviço retorna apenas filmes e utiliza o metodo de consulta por texto similar</p>

<b>Criação do jar</b>

Na pasta do projeto executar:

./gradlew clean

./gradlew fatjar

Sera gerado o arquivo "IMDbSearcher.jar" na pasta "build\lib" Este arquivo já terá todas as bibliotecas necessárias para a execução



<h1>Uso</h1>

<b>iniciar serviço:</b>

java -jar IMDbSearcher.jar [porta IP]

Exemplo: java -jar IMDbSearcher.jar 3300

<b>cliente:</b>

Para testar utilizar Telnet, Putty ou otro cliente TCP/IP, conectar no IP do servidor usando a porta IP

Exemplo windows: Telnet localhost 3300

Após conectar enviar as consultas com a seguinte estrutura:

Tamanho da mensagem:texto a pesquisar

Por exemplo:

5:carro

O servidor responderá com uma lista de filmes no mesmo formato da consulta:

Tamanho da resposta:resposta

Por exemplo:

5422:Carros (2006)</br>
Carros 3 (2017)</br>
Carros 2 (2011)</br>
Carruagens de Fogo (1981) aka "Carrozas de fuego"</br>
Carros Usados (1980)</br>
Operação Red Sparrow (2018)</br>
Carrie, a Estranha (1976)</br>
Carrie, a Estranha (2013)</br>
...

Para finalizar a conexão, enviar x








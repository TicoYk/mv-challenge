<h1 align="center">MV-CHALLENGE</h1>

<p align="center">Criado no dia 11-03-2021 as 14 horas com prazo para dia 14-03-2021</p>
<p align="center">Projeto tem como objetivo completar o <a href="https://drive.google.com/drive/folders/1uNhhIcLIA7ly8FgMG4UZRcshwetLxcKo?usp=sharing">Desafio da MV</a>
</br>

Nesse desafio a tecnologia escolhida foi Spring Boot Versão <b>2.4.3</b> com ThymeLeaf para MVC e api em Rest exclusivamente para relatórios e Transações,
com <b>Maven</b> como gerenciador de dependências e builder e com banco de dados Oracle DB.

Devido a escolha do Spring Boot, o projeto seguiu alguns padrões como DDD e a utilização de Injeção de Dependência via Construtor 
para evitar injeção via atributo(Considerado má prática), utilização de Serviços para trabalhar com a ORM, Controladores para a Camada de comunicação Cliente<->Servidor
e Inversão de Controle com a Spring Context(Component Scan) e a utilização de anotações ao invés de configuração via XML.

A abordagem, foi a de seguir o conceito do Spring Boot, que é a de Convenção acima de Customização, por isso muitas vezes, alguns caminhos,
seriam mais fáceis ou até mais perfomáticos, caso tivessem tido uma outra abordagem, porém nem sempre mais seguros e mais manuteníveis.

## 📝 Documentação

A documentação do desenvolvimento se baseou na criação de issues, milestones e commits.

Obs: O diagrama de relacionamento inicial foi criado e se encontra nas Issues.

<a href="https://github.com/TicoYk/mv-challenge/wiki">WIKI</a>

<a href="https://github.com/TicoYk/mv-challenge/issues?q=is%3Aopen+is%3Aissue">ISSUES ABERTAS</a>

<a href="https://github.com/TicoYk/mv-challenge/issues?q=is%3Aissue+is%3Aclosed">ISSUES FECHADAS</a>

<a href="https://drive.google.com/drive/u/0/folders/1uNhhIcLIA7ly8FgMG4UZRcshwetLxcKo"> DOCUMENTOS </a>

<a href="https://www.getpostman.com/collections/19d0f155421e8eb35102"> POSTMAN </a>
( Somente Relatórios estão por Requisição a outra Parte está em WEB MVC rota /clientes )

<a href="https://github.com/TicoYk/mv-challenge/issues/3">TESTES </a>

## 🧐 Problema <a name = "problem_statement"></a>

O Problema em questão era criar um sistema em que estivesse disponível o Crud de Clientes, Contas, Telefones e gerasse 4 tipos diferentes 
de relatórios relacionado as Transações dos Clientes, isso em um prazo de 4 dias.

- IDEAL: O Ideal seria que o Sistema cumprisse todas as necessidades do desafio, junto com a implementação de testes, e uma documentação,
e um Schema bem estruturado e definido.

- REALIDADE: Devido ao prazo de 4 dias, a abordagem ao problema, foi de documentar e planejar de forma minimalista e muita "hands on",
acredito que o sistema corresponda com as necessidades de forma que seja possível o sistema funcionar.

- CONSEQUÊNCIAS: Caso o "caminho feliz" não seja seguido, muito provavelmente a aplicação pode acabar dando throw de alguma exceção e
existem brechas que podem sofrer exploits.(Não delete o cliente PJ XPTO, por favor.)😆

## 💡 Próximos Passos <a name = "idea"></a>

Os próximos passos seriam os de criar caso de testes para as transações e relatórios, procurar uma solução onde fosse possível
migrar a responsabilidade de manter a conta e a empresa XPTO, de forma que o pagamento de tarifa fosse realizado via chamada de api, pela 
aplicação(back-end).

## 🤔 Como começar?  <a name = "getting_started"></a>

Precisará ter os seguintes requisitos: 
- Java 11

- Maven

- Oracle DB

- Executar o SQL da função no DB
    Estritamente necessário!
<pre>
    CREATE OR REPLACE FUNCTION subtrairData(minhaData in DATE, diferencaDia in number)
    return DATE
    AS
    begin
        return minhaData - NUMTODSINTERVAL(diferencaDia, 'day');
    end;
</pre>

- E uma boa IDE ou Editor de Texto

- Clique no Link para os próximos passos-> <a href="https://github.com/TicoYk/mv-challenge/wiki/Getting-Started"> Getting Started </a>

*A criação de banco de dados, foi deixada como responsalidade da Spring Data JPA,
apesar de ter sido criado um modelo inicial a medida que se foi desenvolvendo foi se 
encontrando mais problemas na modelagem, dessa forma deixando para que o Domínio(Models/Entity) 
criasse a estrutura do banco de dados.

## ✍️ Autor

- [Dídimo Yokoyama de Sousa](https://github.com/ticoyk)

Meu linkedin pode ser encontrado atráves desse <a href="https://www.linkedin.com/in/d%C3%ADdimo-yokoyama-de-sousa-6a09ab13b/">link</a>

## 🎉 Reconhecimento

Google, Equipe Spring Boot e Muitos outros.

Muito obrigado MV, agradeço pela oportunidade, consegui aprender muito com este desafio e consegui encontrar áreas em que devo melhorar.

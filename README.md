# Insider-Threat

Repositório criado com fins didáticos para o projeto final da disciplina 
IMD0040 - Linguagem de Programação II do Bacharelado em Tecnologia da Informação da Universidade Federal do Rio Grande do Norte(UFRN).

Este repositório contém os arquivos correspondentes ao projeto final da disciplina IMD0040 - Linguagem de Programação II, ofertada para o Bacheralado em Tecnlogia de Informação(BTI) na Universidade Federal do Rio Grande do Norte(UFRN).

## Descrição do projeto

A partir da leitura de logs de usuários de uma empresa fictícia, este projeto tem como objetivo criar uma representação de árvore com uma representação visualmente melhor e mais eficiente do que simplesmente planilhas de dados. Após a construção da árvore são analisados os histogramas de atividade de cada usuário e identificados os outliers. Após a identificação dos usuários suspeitos o utilizador do sistema pode verificar o perfil de algum dos suspeitos para confirmar (ou não) as suspeitas.

## Execução

Para compilar o programa utilize o seguinte comando no diretório raiz do projeto:

`javac -d . -classpath src src/Main.java`

Antes de executar o programa é necessário que existam arquivos de log com a formatação correta para serem utilizados.
Neste [_link_](https://box.imd.ufrn.br/index.php/s/0JPj31JpAKp14JH) pode-se baixar a pasta r1 para este fim.

Para executá-lo utilize `java Main <diretorio>` onde o diretorio é
simplesmente o local onde estão armazenados os logs. caso nenhum argumento seja
dado por padrão o programa agirá como se tivesse sido utilizado `java Main r1`. (Não funcionará sem a pasta r1 do link acima).

# Autores

- [_Max William Souto Filgueira_](https://github.com/maxwillf)  
- [_João Pedro de Amorim Paula_ ](https://github.com/jpprime)

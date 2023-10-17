# IACuboMagico2x2
## Atividade de Inteligencia Artifical
Implementação do algoritmo A* para resolução do cubo mágico 2x2

# Classes
## Problema
"Problema" é uma classe abstrata que definirá os métodos que todos os problemas de maneira geral teriam,
como passo anterior, que define qual o passo que foi utilizado para chegar nesse Estado, verificar se aquela
istancia de problema é a solução, e gerar os problemas filhos, que são problemas que derivam das ações que podem
ser realizadas e retonar uma heurista, caso seja implementado um problema que não possui heuristica, ele por 
padrão retornara 0.

## Estado
"Estado" é uma classe que representa um nó da arvore de busca, cada Estado possui uma instancia do problema,
o estado pode gerar estados filhos, que serão estados com instâncias filhas do problema, porém a classe estado
verifica a repetição de estados e evita criar estados desnecessariamente.
Quando um Estado possui a instância de um problema que é a solução, ele recursivamente altera o estados anteriores a ele
na arvore como fazendo parte do caminho que contém a solução.

## Main
A classe principal, que irá executar a busca da solução, ela instância um estado que será o estado inicial,
esse estado inicial é o ponto de partida da busca, ele e os próximos nós são adicionados em uma fila de prioridade
e é sempre pego o próximo nó que possui a menor heuristica do estado, que é a soma da heuristica com a profundidade
do estado. Como a heuristica não é admissível, ele não encontra o caminho perfeito, mas, foi observado que existe
uma proporção entre o custo da heuristica e o custo da profundidade que trás resultados melhores.
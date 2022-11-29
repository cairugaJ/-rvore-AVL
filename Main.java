import java.util.LinkedList;

/**
 *
 * Classe main que executa o projeto - Testa a árvore e seus algoritmos
 *
 * @author João Enrique Rego Cairuga
 * @version 2022-11-19
 */
public class Main {
    public static void main(String[] args) {
        /**
         • Instanciar a árvore implementada; x
         • Incluir os números 1,2,3,4,5,6,7,8,9, nesta ordem, na árvore;x
         • Apresentar a altura da árvore; x
         • Chamar o método “geraDot()” para a árvore; x
         • Limpar o conteúdo da árvore; x
         • Incluir os números 9,8,7,6,5,4,3,2,1, nesta ordem, na árvore; x
         • Apresentar o conteúdo da árvore usando um caminhamento central; x
         • Retornar um clone da árvore; x
         • Chamar o método “geraDot()” para o clone da árvore x
         */
        AVL teste = new AVL();

        teste.add(1);
        teste.add(2);
        teste.add(3);
        teste.add(4);
        teste.add(5);
        teste.add(6);
        teste.add(7);
        teste.add(8);
        teste.add(9);

        System.out.println("Altura da arvore: " + teste.height());

       teste.GeraDOT();

        teste.clear();

        teste.add(9);
        teste.add(8);
        teste.add(7);
        teste.add(6);
        teste.add(5);
        teste.add(4);
        teste.add(3);
        teste.add(2);
        teste.add(1);
        
        LinkedList<Integer> camCentral = teste.positionsCentral();
        
        for (int i = 0; i < camCentral.size(); i++) {
            System.out.print(camCentral.get(i) + ", ");
        }

        System.out.println();

        AVL clone = teste.clone();

        clone.GeraDOT();






    }
}

/**
 *
 * Classe da Árvore AVL
 * @author João Enrique Rego Cairuga e Gabriele Colares Severino
 * @version 2022-11-19
 */
public class AVL {

    //Atributos
    private int count = 0;

    private Nodo raiz = null;

     /**
     *
     * Classe Nodo
     * @author  João Enrique Rego Cairuga
     * @version 2022-11-19
     */
    private static final class Nodo{
        //Atributos
        public Nodo pai;

        public Nodo filhoD;

        public Nodo filhoE;

        public int height;

        public Integer elem;

        private int balan; //Checa se o nodo está balanceado

        //Construtor
        public Nodo(Integer elem){
            pai = null;
            filhoD = null;
            filhoE = null;
            this.elem = elem;
            balan = 0;
            int height;
        }
    }

    //Construtor
    public AVL(){}

    
    /**
     * Adiciona elemento na arvore
     * @param element elemento a ser adicionado
     */
    public void add(int element) {
        Nodo aux = searchNodeRef(element, raiz);

        
    }
    
    /**
     * altura da arvore
     * @return o numero da altura da arvore
     */
    public int height() {
        return 1;
    }

    /**
     * Clona uma arvore
     * @return o clone da arvore
     */
    public AVL clone() {
        return;
    }

    //Getters
    /**
     * Getter do count
     * @return o número de elementos na árvore
     */
    public int size(){
        return count;
    }

    /**
     * Getter da raiz
     * @return nodo raiz
     */
    public Nodo getRaiz() {
        return raiz;
    }

    //Métodos
    /**
     * Reinicia a árvore mudando o valor de sua raiz para null e count para 0
     */
    public void clear() {
        count = 0;
        raiz = null;
    }

    /**
     * Verifica se a árvore está vazia
     * @return true se a árvore estiver vazia
     */
    public boolean isEmpty(){
        return (count == 0);
    }

    /**
     * Checa se existe um nodo na árvore que contenha o elemento elem
     * @param elem
     * @return true se encontrou, null se o elemento não está na árvore
     */
    public boolean contains(Integer elem){
        Nodo teste = searchNodeRef(elem, raiz);
        return (teste != null);
    }

    /**
     * Busca nodo contendo o element a partir do nodo alvo
     * @param element
     * @param alvo
     * @return nodo encontrado ou null se não encontrar
     */
    private Nodo searchNodeRef(Integer element, Nodo alvo) {
        if (element == null || alvo == null)
            return null;
        if (element == alvo.elem)
            return alvo;
        if (element < alvo.elem)
            return searchNodeRef(element, alvo.filhoE);
        else
            return searchNodeRef(element, alvo.filhoD);
    }

    public Nodo getParent(Integer elem){
        Nodo filho = searchNodeRef(elem, raiz);
        if(filho== null) return null;
        return filho.pai;

    }

    private int height(Nodo node) {
        return node != null ? node.height : -1;
      }


   
}

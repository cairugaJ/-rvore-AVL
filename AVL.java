import java.util.LinkedList;

/**
 *
 * Classe da Árvore AVL
 * @author João Enrique Rego Cairuga e Gabriele Colares Severino
 * @version 2022-11-28
 */
public class AVL {

    //Atributos
    private int count = 0;

    private Node root = null;

     /**
     * Classe Nodo
     * @author  João Enrique Rego Cairuga
     * @version 2022-11-19
     */
    
    private static final class Node {

        public Node father;
        public Node left;
        public Node right;
        public Integer elem;
        public int height = 1;

        public Node(Integer elem) {
            father = null;
            left = null;
            right = null;
            this.elem = elem;
        }
    }

    //Construtor
    public AVL(){}

    private int height (Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    private Node add(Integer elem) {
        /* 1.  Perform the normal BST rotation */
        Node node = new Node(elem);
        
        if (elem < node.elem)
            node.left.elem  = elem;
        else
            node.right.elem = elem;

        /* 2. Update height of this ancestor node */
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        /* 3. Get the balance factor of this ancestor node to check whether
           this node became unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && elem < node.left.elem)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && elem > node.right.elem)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && elem > node.left.elem)
        {
            node.left =  leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && elem < node.right.elem)
        {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right))+1;
        x.height = Math.max(height(x.left), height(x.right))+1;

        // Return new root
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        //  Update heights
        x.height = Math.max(height(x.left), height(x.right))+1;
        y.height = Math.max(height(y.left), height(y.right))+1;

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    private int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    public void preOrder(Node root) {
        if (root != null) {
            preOrder(root.left);
            System.out.printf("%d ", root.elem);
            preOrder(root.right);
        }
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
    public Node getRaiz() {
        return root;
    }

    //Métodos
    /**
     * Reinicia a árvore mudando o valor de sua raiz para null e count para 0
     */
    public void clear() {
        count = 0;
        root = null;
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
        Node teste = searchNodeRef(elem, root);
        return (teste != null);
    }

    /**
     * Busca nodo contendo o.elem a partir do nodo alvo
     * @param.elem
     * @param alvo
     * @return nodo encontrado ou null se não encontrar
     */
    private Node searchNodeRef(Integer elem, Node alvo) {
        if (elem == null || alvo == null)
            return null;
        if (elem == alvo.elem)
            return alvo;
        if (elem < alvo.elem)
            return searchNodeRef(elem, alvo.left);
        else
            return searchNodeRef(elem, alvo.right);
    }

    public Node getParent(Integer elem){
        Node filho = searchNodeRef(elem, root);
        if(filho== null) return null;
        return filho.father;

    }

    public LinkedList<Integer> positionsPre() {
        LinkedList<Integer> res = new LinkedList<>();
        positionsPreAux(root, res);
        return res;
    }

    private void positionsPreAux(Node n, LinkedList<Integer> res) {
        if (n != null) {
            res.add(n.elem); // Visita o nodo
            positionsPreAux(n.left, res); // Visita a subárvore da esquerda
            positionsPreAux(n.right, res); // Visita a subárvore da direita
        }
    }

    public LinkedList<Integer> positionsPos() {
        LinkedList<Integer> res = new LinkedList<Integer>();
        positionsPosAux(root, res);
        return res;
    }

    private void positionsPosAux(Node n, LinkedList<Integer> res) {
        if (n != null) {
            positionsPosAux(n.left, res); // Visita a subárvore da esquerda
            positionsPosAux(n.right, res); // Visita a subárvore da direita
            res.add(n.elem); // Visita o nodo
        }
    }

    public LinkedList positionsCentral() {
        LinkedList<Integer> res = new LinkedList<Integer>();
        positionsCentralAux(root, res);
        return res;
    }

    private void positionsCentralAux(Node n, LinkedList<Integer> res) {
        if (n != null) {
            positionsCentralAux(n.left, res); // Visita a subárvore da esquerda
            res.add(n.elem); // Visita o nodo
            positionsCentralAux(n.right, res); // Visita a subárvore da direita
        }
    }

    public LinkedList<Integer> positionsWidth() {
        Queue<Node> fila = new Queue<>();
        Node atual = null;
        LinkedList<Integer> res = new LinkedList<Integer>();
        if (root != null) {
            fila.enqueue(root);
            while (!fila.isEmpty()) {
                atual = fila.dequeue();
                if (atual.left != null) {
                    fila.enqueue(atual.left);
                }
                if (atual.right != null) {
                    fila.enqueue(atual.right);
                }
                res.add(atual.elem);
            }
        }
        return res;
    }

    public AVL clone() {
        AVL tree = new AVL();
        clone(root, tree);
        return tree;
    }

    private void clone(Node n, AVL tree) {
        if (n != null) {
            tree.add(n.elem); // Visita o nodo
            clone(n.left, tree); // Visita a subárvore da esquerda
            clone(n.right, tree); // Visita a subárvore da direita
        }
    }

    private void GeraConexoesDOT(Node nodo) {
        if (nodo == null) {
            return;
        }

        GeraConexoesDOT(nodo.left);
        // "nodeA":esq -> "nodeB" [color="0.650 0.700 0.700"]
        if (nodo.left != null) {
            System.out.println("\"node" + nodo.elem + "\":esq -> \"node" + nodo.left.elem + "\" " + "\n");
        }

        GeraConexoesDOT(nodo.right);
        // "nodeA":dir -> "nodeB";
        if (nodo.right != null) {
            System.out.println("\"node" + nodo.elem + "\":dir -> \"node" + nodo.right.elem + "\" " + "\n");
        }
        // "[label = " << nodo->hDir << "]" <<endl;
    }

    private void GeraNodosDOT(Node nodo) {
        if (nodo == null) {
            return;
        }
        GeraNodosDOT(nodo.left);
        // node10[label = "<esq> | 10 | <dir> "];
        System.out.println("node" + nodo.elem + "[label = \"<esq> | " + nodo.elem + " | <dir> \"]" + "\n");
        GeraNodosDOT(nodo.right);
    }

    public void GeraConexoesDOT() {
        GeraConexoesDOT(root);
    }

    public void GeraNodosDOT() {
        GeraNodosDOT(root);
    }

    // Gera uma saida no formato DOT
    // Esta saida pode ser visualizada no GraphViz
    // Versoes online do GraphViz pode ser encontradas em
    // http://www.webgraphviz.com/
    // http://viz-js.com/
    // https://dreampuf.github.io/GraphvizOnline
    public void GeraDOT() {
        System.out.println("digraph g { \nnode [shape = record,height=.1];\n" + "\n");

        GeraNodosDOT();
        System.out.println("");
        GeraConexoesDOT(root);
        System.out.println("}" + "\n");
    }

}
   


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
        public int bf;
        public int height;

        public Node(Integer elem) {
            father = null;
            left = null;
            right = null;
            this.elem = elem;
            this.bf = 0;
            this.height = 1;
        }
    }

    //Construtor
    public AVL(){}

    /**
     * Retorna a altura da arvore
     * @param N nodo do qual é pesquisada a altura
     * @return a altura do nodo
     */
    

    //public int height() {
        
    //}

    public int height() {
        return height(root);
    }

    int height(Node N) {
        if (N == null)
            return 0;
 
        return N.height;
    }
 
    // A utility function to get maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }


    	// update the balance factor the node
	private void updateBalance(Node node) {
		if (node.bf < -1 || node.bf > 1) {
			rebalance(node);
			return;
		}

		if (node.father != null) {
			if (node == node.father.left) {
				node.father.bf -= 1;
			} 

			if (node == node.father.right) {
				node.father.bf += 1;
			}

			if (node.father.bf != 0) {
				updateBalance(node.father);
			}
		}
	}

	// rebalance the tree
	private void rebalance(Node node) {
		if (node.bf > 0) {
			if (node.right.bf < 0) {
				rightRotate(node.right);
				leftRotate(node);
			} else {
				leftRotate(node);
			}
		} else if (node.bf < 0) {
			if (node.left.bf > 0) {
				leftRotate(node.left);
				rightRotate(node);
			} else {
				rightRotate(node);
			}
		}
	}

	// rotate left at node x
	private void leftRotate(Node x) {
		Node y = x.right;
		x.right = y.left;
		if (y.left != null) {
			y.left.father = x;
		}
		y.father = x.father;
		if (x.father == null) {
			this.root = y;
		} else if (x == x.father.left) {
			x.father.left = y;
		} else {
			x.father.right = y;
		}
		y.left = x;
		x.father = y;

		// update the balance factor
		x.bf = x.bf - 1 - Math.max(0, y.bf);
		y.bf = y.bf - 1 + Math.min(0, x.bf);

        // update the heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
	}

	// rotate right at node x
	private void rightRotate(Node x) {
		Node y = x.left;
		x.left = y.right;
		if (y.right != null) {
			y.right.father = x;
		}
		y.father = x.father;
		if (x.father == null) {
			this.root = y;
		} else if (x == x.father.right) {
			x.father.right = y;
		} else {
			x.father.left = y;
		}
		y.right = x;
		x.father = y;

		// update the balance factor
		x.bf = x.bf + 1 - Math.min(0, y.bf);
		y.bf = y.bf + 1 + Math.max(0, x.bf);

        // update the height
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
	}


	// insert the elem to the tree in its appropriate position
	public void add(int elem) { //O(log n)
		// PART 1: Ordinary BST insert
		Node node = new Node(elem);
		Node y = null;
		Node x = this.root;

		while (x != null) {
			y = x;
			if (node.elem < x.elem) {
				x = x.left;
			} else {
				x = x.right;
			}
		}

		// y is father of x
		node.father = y;
		if (y == null) {
			root = node;
		} else if (node.elem < y.elem) {
			y.left = node;
		} else {
			y.right = node;
		}

		// PART 2: re-balance the node if necessary
		updateBalance(node);
        count++;
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

    public LinkedList<Integer> positionsCentral() {
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

    public AVL clone() { // O(log n)
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
   


package adjoin.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author zhaohongxu
 * 
 */
public class Graphic {

	private Node[] nodes;// 图中所包含的的节点

	public Node[] getNodes() {
		return nodes;
	}

	public void setNodes(Node[] nodes) {
		this.nodes = nodes;
	}

	public Graphic(Node[] nodes) {
		super();
		this.nodes = nodes;
	}

	public Graphic() {
		super();
	}

	/**
	 * 初始化图中的每一个节点，并且节点的每一个访问标记是false，id 是从0开始依次递增，值是id+3之后的值
	 * 
	 * @param metrix
	 *            邻接矩阵
	 */
	public void initialGraphic(String[][] metrix) {

		int number = metrix.length;

		nodes = new Node[number];
		for (int i = 0; i < number; i++) {
			nodes[i] = new Node();
			nodes[i].setNodeId(String.valueOf(i + 1));
			nodes[i].setVisited(false);
			nodes[i].setNextNode(null);
		}
	}

	/**
	 * 根据给定的二维矩阵构造一个邻接表
	 * 
	 * @param metrix
	 */
	public void makeAdjoinListAccordingToTwoMetrix(String[][] metrix) {

		int line = metrix.length;// 二维矩阵的行数
		int cols = metrix[0].length;// 二维矩阵的列数

		for (int i = 0; i < line; i++) {
			Node tempNode = nodes[i];
			Node tempNode2 = new Node();
			int number = 0;
			for (int j = 0; j < cols; j++) {
				if (!metrix[i][j].equals("0")) {

					Node node = new Node();
					node.setNextNode(null);
					node.setNodeId(String.valueOf(j + 1));
					node.setVisited(false);

					nodes[i].setNextNode(node);
					nodes[i] = node;
					number++;
					if (number == 1) {// 记录指向的第一个元素
						tempNode2 = nodes[i];
					}
				}
			}
			nodes[i] = tempNode;
			if (number >= 1) {
				nodes[i].setNextNode(tempNode2);
			}
		}
	}

	/**
	 * 输出节点的链接情况
	 */
	public void printGraphNodesLinkInfo(String[][] metrix) {
		int number = metrix.length;
		for (int i = 0; i < number; i++) {
			System.out.println("第" + (i + 1) + "个节点的链接情况");
			Node tempNode = nodes[i];
			while ((tempNode != null)) {// && (!nodes[i].isVisited())
				if (tempNode.getNextNode() == null) {
					System.out.print(tempNode.getNodeId());
				} else {
					System.out.print(tempNode.getNodeId() + "-->");
				}
				tempNode = tempNode.getNextNode();
			}
			System.out.println();
		}
	}

	/**
	 * 利用深度优先算法查询拓扑圈，并且根据该最长路径 ，寻找回路； 一、找到最长的路径 ；二、判断最后一个节点的相邻节点的Id是否与其父节点的Id相同；
	 * 三、若不相同，则比较父节点前面节点的Id，若是相同，则构成回路，并输出路径； 四、若是相同，则比较下一个相邻节点，转到步骤二；
	 * 五、判断相邻节点是否已经比较完，若是比较完，则返回上一个节点，继续步骤二；若是没有比较完，则转向步骤二；
	 * 
	 * @param number
	 *            用于控制由构成环路的结点数目
	 */
	public void execDFSAccordingToAdjoinList() {

		int number = nodes.length;
		for (int i = 0; i < number; i++) {
			List<String> longRoutineList = new ArrayList<String>();

			longRoutineList = findLongRoutine(nodes, i);// 寻找从第i个节点出发构造的最长路径，并将它们的节点编号保存在一个列表中。
			printLongRoutineList(longRoutineList, i);// 输出最长路径的长度
			// 转向数组中的该节点

			// 如果节点未被访问过，则将其加入栈中

			// 继续进行深度查询；

		}
	}

	/**
	 * 
	 * 输出最长路径的长度
	 * 
	 * @param longRoutineList
	 * @param k
	 *            从第k个顶点出发
	 */
	private void printLongRoutineList(List<String> longRoutineList, int k) {
		int size = longRoutineList.size();
		if (size < 3) {
			System.out.println("节点的个数小于3,不能够输出！");
		} else {
			System.out.println("从" + (k + 1) + "个顶点出发的最长路径为：");
			for (int i = 0; i < size; i++) {
				System.out.print(longRoutineList.get(i) + "-->");
			}
			System.out.println();
		}

	}

	/**
	 * 寻找从第k个节点出发构造的最长路径，并将它们的节点编号保存在一个列表中。
	 * 
	 * @param nodes2
	 *            邻接表
	 * @param k
	 *            从第k个节点出发
	 * @return 找到的最长路径
	 */
	private List<String> findLongRoutine(Node[] nodes2, int k) {
		List<String> longRoutineList = new ArrayList<String>();

		Node tempNode = nodes2[k];
		String nodeId = tempNode.getNodeId();
		longRoutineList.add(nodeId);// 加进列表中
		makeVisitedNodeTrue(nodeId);// 具有相同节点号的节点全部置为true;
		tempNode = tempNode.getNextNode();
		if (tempNode != null) {
			int tempNodeId = Integer.parseInt(String.valueOf(tempNode
					.getNodeId()));
			while (!nodes2[tempNodeId - 1].isVisited()) {
				longRoutineList.add(tempNode.getNodeId());// 加进列表中
				makeVisitedNodeTrue(tempNode.getNodeId());// 具有相同节点号的节点全部置为true;
				tempNode = nodes2[tempNodeId - 1].getNextNode();
				while (tempNode.isVisited()) {
					tempNode = tempNode.getNextNode();
				}
				tempNodeId = Integer.parseInt(String.valueOf(tempNode
						.getNodeId()));
			}

		}

		// 重新将节点的访问标记置为false
		makeAllNodesVisitedFalse(nodes2);
		return longRoutineList;
	}

	/**
	 * @param nodes2
	 */
	private void makeAllNodesVisitedFalse(Node[] nodes2) {
		int number = nodes2.length;
		for (int i = 0; i < number; i++) {

			Node tempNode = nodes2[i];
			while (tempNode.getNextNode() != null) {
				tempNode.setVisited(false);
				tempNode = tempNode.getNextNode();
			}
		}
	}

	/**
	 * 
	 * 具有相同节点号的节点全部置为true;
	 * 
	 * @param nodeId
	 */
	private void makeVisitedNodeTrue(String nodeId) {
		int number = nodes.length;
		for (int i = 0; i < number; i++) {
			Node tempNode = nodes[i];
			while (tempNode.getNextNode() != null) {
				if (tempNode.getNodeId().equals(nodeId)) {
					tempNode.setVisited(true);
				}
				tempNode = tempNode.getNextNode();
			}
		}
	}

	/**
	 * 
	 * 具有相同节点号的节点全部置为false;
	 * 
	 * @param nodeId
	 */
	private void makeVisitedNodeFalse(String nodeId) {
		int number = nodes.length;
		for (int i = 0; i < number; i++) {
			Node tempNode = nodes[i];
			while (tempNode.getNextNode() != null) {
				if (tempNode.getNodeId().equals(nodeId)) {
					tempNode.setVisited(false);
				}
				tempNode = tempNode.getNextNode();
			}
		}
	}

	/**
	 * 寻找由特定node编号出发的顶点的路径
	 * 
	 * @param node
	 *            出发顶点
	 * @return
	 */
	public String findAllRoutineAccordingToSpecifyNode(String nodeID) {

		// 将以结点为nodeID出发的节点进行深度进栈
		Stack<Node> getStack = pushStackAccordingNodeId(nodeID);
		popStackAccordingStack(getStack);// 根据给定的栈执行出栈操作
		return nodeID;

	}

	/**
	 * 根据给定的栈执行出栈操作
	 * 
	 * @param getStack
	 *            给定的栈
	 */
	private void popStackAccordingStack(Stack<Node> getStack) {
		System.out.println("Hello I will pop.");

	}

	Stack<Node> stackNodes = new Stack<Node>();

	/**
	 * 
	 * 存在一个回溯的问题； 将以结点为nodeID的出发节点并且与该节点相连接的节点进行深度进栈
	 * 
	 * @param nodeID
	 */
	private Stack<Node> pushStackAccordingNodeId(String nodeID) {

		Node tempNode = nodes[Integer.parseInt(nodeID) - 1];

		String nodeId = tempNode.getNodeId();

		while (tempNode.isVisited()) {
			if (tempNode.getNextNode() != null) {
				tempNode = tempNode.getNextNode();
			} else {
				break;
			}
		}
		while (!tempNode.isVisited()) {
			stackNodes.push(tempNode);
			makeVisitedNodeTrue(nodeId);
			if (tempNode.getNextNode() != null) {
				tempNode = tempNode.getNextNode();
			} else {
				break;
			}

			while (tempNode.isVisited()) {
				if (tempNode.getNextNode() != null) {
					tempNode = tempNode.getNextNode();
				} else {
					break;
				}
			}
			nodeId = tempNode.getNodeId();
			pushStackAccordingNodeId(nodeId);
		}
		return stackNodes;

	}

	/**
	 * 
	 * 执行出栈操作，并输出其中的环路圈
	 * 
	 * @param tempStack
	 * @return
	 */
	private String execStackPop(Stack<Node> tempStack) {

		if (tempStack.isEmpty()) {
			System.out.println("The stack is null,no elements");
		} else {
			int number = tempStack.capacity();
		}
		tempStack.pop();
		return null;
	}

	/**
	 * 寻找结点号为nodeId的父节点
	 * 
	 * @param nodeId
	 */
	public void findFatherNode(String nodeId) {

	}
}

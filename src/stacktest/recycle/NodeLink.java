package stacktest.recycle;

import java.util.Stack;

/**
 * 
 * 邻接表
 * 
 * @author zhaohongxu
 * 
 */
public class NodeLink {

	private Stack<Node> stack = new Stack<Node>();// 用于保存进入栈内的数据
	private Node[] nodes;

	public NodeLink() {
		super();
		stack = new Stack<Node>();
	}

	public Node[] getNodes() {
		return nodes;
	}

	public void setNodes(Node[] nodes) {
		this.nodes = nodes;
	}

	public Stack<Node> getStack() {
		return stack;
	}

	public void setStack(Stack<Node> stack) {
		this.stack = stack;
	}

	/**
	 * 初始化每一个链中的每个节点
	 * 
	 * @param size
	 */
	public NodeLink(int size) {
		stack = new Stack<Node>();
		nodes = new Node[size];
		for (int i = 0; i < size; i++) {
			nodes[i] = new Node();
			nodes[i].setId(i + 1);
			nodes[i].setVisited(false);
			nodes[i].setValue(String.valueOf(2 * (i + 1) + 1));
		}
	}

	/**
	 * 根据二维矩阵构造邻接表
	 * 
	 * @param metrix
	 *            指定二维数组
	 * @return 返回构造的邻接表
	 */
	public Node[] initialNodeLink(String[][] metrix) {

		int line = metrix.length;
		int cols = metrix[0].length;
		for (int i = 0; i < line; i++) {
			Node tempNode = nodes[i];
			for (int j = 0; j < cols; j++) {
				if (!metrix[i][j].equals("0")) {
					Node node = new Node();
					node.setId(j + 1);
					node.setValue(String.valueOf(2 * (j + 1) + 1));
					node.setVisited(false);
					node.setNextNode(null);
					tempNode.setNextNode(node);
					tempNode = tempNode.getNextNode();
				}
			}
		}
		return nodes;
	}

	/**
	 * 
	 * 将邻接链表信息输出到控制台上 输出形式如：(1,3)-->(2,5)....
	 * 
	 * @param nodes
	 *            指定的节点数组
	 */
	public void printNodeLinkInfo(Node[] nodes) {
		int size = nodes.length;
		for (int i = 0; i < size; i++) {
			Node tempNode = nodes[i];
			System.out.print("The " + (i + 1) + "th node's link info is:");
			while (tempNode.getNextNode() != null) {
				System.out.print("(" + tempNode.getId() + ","
						+ tempNode.getValue() + ")" + "-->");// +
																// tempNode.isVisited()
				tempNode = tempNode.getNextNode();
			}
			System.out.println("(" + tempNode.getId() + ","
					+ tempNode.getValue() + "),");// + tempNode.isVisited()
		}
	}

	/**
	 * 
	 * 另邻接表中所有的节点的访问标示均标为false
	 * 
	 * @param nodes
	 *            指定节点数组
	 * @return 返回处理后的节点数组
	 */
	public Node[] makeAllNodesFalse(Node[] nodes) {

		int size = nodes.length;

		for (int i = 0; i < size; i++) {
			Node tempNode = nodes[i];
			while (tempNode.getNextNode() != null) {
				tempNode.setVisited(false);
				tempNode = tempNode.getNextNode();
			}
			tempNode.setVisited(false);
		}
		return nodes;
	}

	/**
	 * 
	 * 令邻接表中所有的节点的访问标示均标为True
	 * 
	 * @param nodes
	 *            指定节点数组
	 * @return 返回处理后的节点数组
	 */
	public Node[] makeAllNodesTrue(Node[] nodes) {
		int size = nodes.length;
		for (int i = 0; i < size; i++) {
			Node tempNode = nodes[i];
			while (tempNode.getNextNode() != null) {
				tempNode.setVisited(true);
				tempNode = tempNode.getNextNode();
			}
			tempNode.setVisited(true);
		}
		return nodes;
	}

	/**
	 * 
	 * 另指定节点号码为id的所有节点的访问标示均为False
	 * 
	 * @param id
	 *            指定的id编号
	 * @param nodes
	 *            指定的节点数组
	 * @return 返回处理后的节点数组
	 */
	public Node[] makeSpecifyNodeFalse(int id, Node[] nodes) {
		int size = nodes.length;
		for (int i = 0; i < size; i++) {
			Node tempNode = nodes[i];
			int nodeId;
			while (tempNode.getNextNode() != null) {
				nodeId = tempNode.getId();
				if (nodeId == id) {
					tempNode.setVisited(false);
				}
				tempNode = tempNode.getNextNode();
			}
			nodeId = tempNode.getId();
			if (nodeId == id) {
				tempNode.setVisited(false);
			}
		}
		return nodes;
	}

	/**
	 * 
	 * 另指定节点号码为id的所有节点的访问标示均为true
	 * 
	 * @param id
	 *            指定的id编号
	 * @param nodes
	 *            指定的节点数组
	 * @return 返回处理后的节点数组
	 */
	public Node[] makeSpecifyNodeTrue(int id, Node[] nodes) {
		int size = nodes.length;
		for (int i = 0; i < size; i++) {
			Node tempNode = nodes[i];
			int nodeId;
			while (tempNode.getNextNode() != null) {
				nodeId = tempNode.getId();
				if (nodeId == id) {
					tempNode.setVisited(true);
				}
				tempNode = tempNode.getNextNode();
			}
			nodeId = tempNode.getId();
			if (nodeId == id) {
				tempNode.setVisited(true);
			}
		}
		return nodes;
	}

	// 从起始编号startid出发，将与之相连的节点进行深度优先访问
	public void pushNodeToStack(int startid) {

		Node tempNode = nodes[startid - 1];
		System.out.println("以" + startid + "为起点的节点的节点开始深度进栈!");
		while (!tempNode.isVisited()) {
			stack.push(tempNode);
			makeSpecifyNodeTrue(startid, nodes);
			if (tempNode.getNextNode() != null) {
				tempNode = tempNode.getNextNode();
				while (tempNode.isVisited()) {
					if (tempNode.getNextNode() != null) {
						tempNode = tempNode.getNextNode();
					} else {// 执行出栈操作，并将具有的回路信息输出到控制台上
						System.out.println("进栈完毕！开始出栈。");
						break;
					}
				}
				startid = tempNode.getId();
				tempNode = nodes[startid - 1];// pushNodeToStack(startid);
			}
		}

		System.out.println("输出栈的第一个元素：-->" + stack.firstElement().getId());
		System.out.println("输出栈的最后一个元素：-->" + stack.lastElement().getId());
		if (!stack.isEmpty()) {
			int size = stack.size();
			// 出栈之前必须检查该节点的链表是否已经全部访问？
			// examPopNodeLinkVisited(popNode);
			if (size >= 3) {
				findRecycleRoutine(stack);// 寻找由出栈点和栈内的点构成的回路
				stack.clear();// 寻找完毕，清空
			} else {
				// 栈清空，使得下次进栈时，
				stack.clear();
			}
		} else {
			System.out.println("栈内没有元素！");
		}
	}

	/**
	 * @param stack2
	 *            提供的剩余栈
	 * @param popNode
	 *            栈的第一个元素
	 */
	private void findRecycleRoutine(Stack<Node> stack2) {
		int size = stack2.size();
		while (size >= 3) {
			Node stackLastNode = stack2.lastElement();
			int i = 0;
			while ((size - i) >= 3) {
				Node stackSecondNode = stack2.get(i);
				boolean result = judeExistsRelationOfLink(stackLastNode,
						stackSecondNode);// 判断节点之间是否存在着父子关系
				if (result) {
					System.out.println("存在着以下所示的回路，此时的i：" + i);
					// 则输出连接情况
					int sizeK = stack2.size() - 1;
					while (sizeK != i) {// 假设条件，需要重新赋值
						System.out.print(stack2.get(sizeK).getId() + "-->");
						sizeK--;
					}
					if (i == 0) {
						System.out.println(stack2.get(i).getId());
					} else {
						System.out.println(stack2.get(i).getId());
					}
					System.out.println();
					// System.out.println(stack2.get(sizeK).getId());//暂时封存
				} else {
					// System.out.println("不存在连接回路了");
				}
				i++;
			}
			stack2.pop();
			size = stack2.size();
		}
	}

	/**
	 * 
	 * 判断第一个节点是否与第二个节点之间存在着连接关系
	 * 
	 * @param stackFirstNode
	 * @param nodeLink
	 */
	private boolean judeExistsRelationOfLink(Node stackFirstNode,
			Node stackSecondNode) {
		Node[] nodeLink = findStackElementsWithFirstElement(stackSecondNode);
		boolean result = false;
		int size = nodeLink.length;
		int i = 0;
		while (size > i) {
			if (nodeLink[i].getId() == stackFirstNode.getId()) {
				result = true;
				break;
			} else {
				i++;
			}
		}
		// System.out.println(result);
		return result;
	}

	/**
	 * 寻找与栈内第一个相连的元素
	 * 
	 * @param stackFirstNode
	 */
	private Node[] findStackElementsWithFirstElement(Node stackFirstNode) {
		int count = 0;
		Node tempNode = stackFirstNode.getNextNode();
		while (tempNode != null) {
			tempNode = tempNode.getNextNode();
			count++;
		}
		Node[] linkNodes = new Node[count];
		tempNode = stackFirstNode.getNextNode();
		int i = 0;
		while (tempNode != null) {
			linkNodes[i] = tempNode;
			i++;
			tempNode = tempNode.getNextNode();
		}
		return linkNodes;
	}
}

package solution;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;

import java.util.Comparator;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Vector;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {
	Vector<JigsawNode> BFS_openList; // 用以保存已发现但未访问的节点
	Vector<JigsawNode> BFS_closeList; // 用以保存已访问的节点

	/**
	 * 拼图构造函数
	 */
	public Solution() {
	}

	/**
	 * 拼图构造函数
	 * @param bNode - 初始状态节点
	 * @param eNode - 目标状态节点
	 */
	public Solution(JigsawNode bNode, JigsawNode eNode) {
		super(bNode, eNode);
	}

	/**
	 *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
	 * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
	 * @return 搜索成功时为true,失败为false
	 */
	public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
		// reset the state
		this.BFS_openList = new Vector<JigsawNode>();
		this.BFS_closeList = new Vector<JigsawNode>();
		this.beginJNode = new JigsawNode(bNode);
        this.endJNode = new JigsawNode(eNode);
        this.currentJNode = null;
		//this.searchedNodeNum = 0;
		super.reset();
		final int DIRS = 4;

		
		// (1)Add begin node to explore list.
		BFS_openList.add(this.beginJNode);

		// (2)If open list is empty, fail to find answer
		// otherwise, find answer in loop
		while (!BFS_openList.isEmpty()) {
			// (2-1)Assign current node with the first node in open list
			// Compare the current node with end node
			// If true, search completed.
			this.currentJNode = BFS_openList.elementAt(0);
			if (this.currentJNode.equals(eNode)) {
				this.getPath();
				break;
			}
			
			// (2-2)Remove current node from open list
			// Add it to close list
			this.BFS_openList.removeElementAt(0);
			this.BFS_closeList.addElement(this.currentJNode);
			//this.searchedNodeNum++;
			
			// (2-3)Add all non-visited adjance nodes of current node to openlist
			JigsawNode[] adjanceNodes = new JigsawNode[]{
				new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode),
				new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode)
			};
			for (int i = 0; i < DIRS; ++i) {
				if (adjanceNodes[i].move(i) &&
				!this.BFS_openList.contains(adjanceNodes[i]) &&
				!this.BFS_closeList.contains(adjanceNodes[i])) {
					BFS_openList.add(adjanceNodes[i]);
				}
			}
		}
		
		System.out.println("Jigsaw AStar Search Result:");
        System.out.println("Begin state:" + this.getBeginJNode().toString());
        System.out.println("End state:" + this.getEndJNode().toString());
    	//System.out.println("Solution Path: ");
        //System.out.println(this.getSolutionPath());
        System.out.println("Total number of searched nodes:" + this.getSearchedNodesNum());
        System.out.println("Depth of the current node is:" + this.getCurrentJNode().getNodeDepth());
        return this.isCompleted();
	}


	/**
	 *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
	 * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
	 * @param jNode - 要计算代价估计值的节点
	 */
	public void estimateValue(JigsawNode jNode) {
		int count = 0; // 后续节点不正确的数码个数
		int ManHattanDis = 0; //曼哈顿距离
		int euclideanDis = 0; //欧几里得距离平方
		int dimension = JigsawNode.getDimension();

		// Count incorrect nodes in rows
		// Blank node does not involve the calculation
		for (int index = 1; index < dimension * dimension; index++) {
			if (index % dimension != 0 &&
				jNode.getNodesState()[index] != 0 &&
				jNode.getNodesState()[index + 1] != 0 &&
				jNode.getNodesState()[index] + 1 !=
				jNode.getNodesState()[index + 1]) {
				count++;
			}
		}

		// Count incorrect nodes in columns
		// Blank node does not involve the calculation
		for (int index = 1; index <= dimension; index++) {
			for (int j = 0; j < dimension - 1; ++j) {
				if (jNode.getNodesState()[j * dimension + index] != 0 &&
					jNode.getNodesState()[(j + 1) * dimension + index] != 0 &&
					jNode.getNodesState()[j * dimension + index] + dimension !=
					jNode.getNodesState()[(j + 1) * dimension + index]) {
					count++;
				}
			}
		}

		// Compute Manhattan distance and the square of eculidean distance of all nodes
		// Blank node does not involve the computation
		for (int i = 1; i <= dimension * dimension; ++i) {
			if (jNode.getNodesState()[i] != 0) {
				for (int j = 1; j <= dimension * dimension; ++j) {
					if (jNode.getNodesState()[i] == this.endJNode.getNodesState()[j]) {
						int curRow = (i - 1) / dimension;
						int curCol = (i - 1) % dimension;
						int endRow = (j - 1) / dimension;
						int endCol = (j - 1) % dimension;
						ManHattanDis += Math.abs(curRow - endRow) + Math.abs(curCol - endCol);
						euclideanDis += Math.pow(curRow - endRow, 2) + Math.pow(curCol - endCol, 2);
						break;
					}
				}
			}
		}

		// Set weight of the cost
		jNode.setEstimatedValue(7 * count + 6 * ManHattanDis + 3 * euclideanDis);
	}
}

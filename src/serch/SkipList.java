package serch;

/**
 * @author shenzhuojun
 * @version 1.0 2022/9/29 9:48 上午 跳表
 */
class SkipList {

    // level层数组 和树高一样，自上而下 0 -> MAX_LEVEL
    private Node[] forward;

    // 级别高度
    private int MAX_LEVEL = 16;

    // 初始化操作
    public SkipList() {
        // 初始化 level 层数组
        forward = new Node[MAX_LEVEL];
        // 这一层要放入 forward 里的 head 节点
        Node currentHead = new Node(-1);
        for (int i = MAX_LEVEL - 1; i >= 0; i--) {
            // 下一层的 head 节点
            Node nextHead = new Node(-1);
            nextHead.down = currentHead;
            // 这层的 head 节点放入
            forward[i] = currentHead;
            currentHead = nextHead;
        }
    }

    // 查询操作
    public boolean search(int target) {
        Node localNode = forward[0];
        // 当前层数，从上层开始查找
        int i = 0;
        while (i < MAX_LEVEL) {
            // 从高层往下找
            if (localNode.data == target) {
                return true;
            } else if (localNode.next != null && localNode.next.data <= target) {
                localNode = localNode.next;
            } else {
                localNode = localNode.down;
                i++;
            }
        }
        return false;
    }

    // 添加操作
    public void add(int num) {
        // 不用判断是否已经添加过了
        Node[] pre_node = new Node[MAX_LEVEL];
        for (int i = MAX_LEVEL - 1; i >= 0; i--) {
            // 查找每一层的前驱节点
            Node per_head = forward[i];
            while (per_head.next != null && per_head.next.data < num) {
                per_head = per_head.next;
            }
            // 记录每一层合适的位置
            pre_node[i] = per_head;
        }
        // 从1开始算
        int levelNum = randomLevel();
        Node target = new Node(num);
        for (int i = 0; i < levelNum; i++) {
            // 创建上一层的节点
            Node tmp = new Node(num);
            tmp.down = target;
            // 从下层开始加节点
            target.next = pre_node[MAX_LEVEL - 1 - i].next;
            pre_node[MAX_LEVEL - 1 - i].next = target;
            target = tmp;
        }
    }

    // 删除操作
    public boolean erase(int num) {
        if (search(num)) {
            for (int i = MAX_LEVEL - 1; i >= 0; i--) {
                // 查找每一层的前驱节点 // 默认为头节点
                Node per_head = forward[i];
                while (per_head.next != null) {
                    if (per_head.next.data == num) {
                        per_head.next = per_head.next.next;
                        break;
                    }
                    per_head = per_head.next;
                }
            }
            return true;
        }
        return false;
    }

    // 看随机数最多能到多少层 每次有50%的概率上升一层
    private int randomLevel() {
        int level = 0;

        // 概率 每个节点对于每层来说每次都有50%的插入该层  插入到队列的概率是100%
        double p = 1d;

        // 当 level < MAX_LEVEL，且随机数小于设定的晋升概率时，level + 1 概率变为原来的一般
        while (Math.random() < p && level < MAX_LEVEL) {
            // 第一个循环肯定进来 变为1
            level++;
            p = p / 2.0;
        }
//        System.out.println(level);
        return level;
    }

    class Node {

        int data; // 用于存放数据
        Node next; // 用于指向同一层的下一个Node
        Node down; // 指向相同数据的下一层

        public Node(int data) {
            this.data = data;
        }
    }
}


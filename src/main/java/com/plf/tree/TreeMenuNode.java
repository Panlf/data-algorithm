package com.plf.tree;

import lombok.Data;

import java.util.List;

/**
 * @author panlf
 * @date 2023/1/23
 */
@Data
public class TreeMenuNode {
    /** 节点ID */
    private Integer id;

    /** 父节点ID：顶级节点为0 */
    private Integer parentId;

    /** 节点名称 */
    private String label;

    /** 子节点 */
    private List<TreeMenuNode> children;

    public TreeMenuNode(Integer id, Integer parentId, String label) {
        this.id = id;
        this.parentId = parentId;
        this.label = label;
    }
}

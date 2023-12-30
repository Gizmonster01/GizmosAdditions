package monster.giz.blocks.types;

public enum GABlockType {
    BASE("base"),
    SLAB("slab"),
    STAIRS("stairs"),
    WALL("wall"),
    FENCE("fence"),
    FENCE_GATE("gate");

    private final String name;

    GABlockType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

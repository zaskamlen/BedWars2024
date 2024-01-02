package com.vimebedwars.game.object.cube;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.*;

public class Cube implements Iterable<Block>, Cloneable, ConfigurationSerializable {

    protected final String worldName;
    protected final int x1, y1, z1;
    protected final int x2, y2, z2;

    /* Cuboid по двум объектам Location, которые представляют любые два угла Cuboid.
     * Примечание: 2 локации должны быть в одном мире.
     *
     * @param l1 - Один из углов
     * @param l2 - Другой угол
     */

    public Cube(Location l1, Location l2) {
        if (!l1.getWorld().equals(l2.getWorld())) {
            throw new IllegalArgumentException("Locations must be on the same world");
        }
        this.worldName = l1.getWorld().getName();
        this.x1 = Math.min(l1.getBlockX(), l2.getBlockX());
        this.y1 = Math.min(l1.getBlockY(), l2.getBlockY());
        this.z1 = Math.min(l1.getBlockZ(), l2.getBlockZ());
        this.x2 = Math.max(l1.getBlockX(), l2.getBlockX());
        this.y2 = Math.max(l1.getBlockY(), l2.getBlockY());
        this.z2 = Math.max(l1.getBlockZ(), l2.getBlockZ());
    }

    /**
     * Construct одноблочный кубоид в заданном местоположении кубоида..
     *
     * @param l1 местоположение кубоида
     */
    public Cube(Location l1) {
        this(l1, l1);
    }

    /**
     * Копирует constructor.
     *
     * @param other - Копирует кубоид
     */
    public Cube(Cube other) {
        this(other.getWorld().getName(), other.x1, other.y1, other.z1, other.x2, other.y2, other.z2);
    }

    /**
     * Construct кубоид в заданном мире и координатах xyz
     *
     * @param world - The Cuboid's world
     * @param x1    - X co-ordinate of corner 1
     * @param y1    - Y co-ordinate of corner 1
     * @param z1    - Z co-ordinate of corner 1
     * @param x2    - X co-ordinate of corner 2
     * @param y2    - Y co-ordinate of corner 2
     * @param z2    - Z co-ordinate of corner 2
     */
    public Cube(World world, int x1, int y1, int z1, int x2, int y2, int z2) {
        this.worldName = world.getName();
        this.x1 = Math.min(x1, x2);
        this.x2 = Math.max(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.y2 = Math.max(y1, y2);
        this.z1 = Math.min(z1, z2);
        this.z2 = Math.max(z1, z2);
    }

    /**
     * Construct кубоид с заданным мировым именем и координатами xyz.
     *
     * @param worldName - The Cuboid's world name
     * @param x1        - X co-ordinate of corner 1
     * @param y1        - Y co-ordinate of corner 1
     * @param z1        - Z co-ordinate of corner 1
     * @param x2        - X co-ordinate of corner 2
     * @param y2        - Y co-ordinate of corner 2
     * @param z2        - Z co-ordinate of corner 2
     */
    private Cube(String worldName, int x1, int y1, int z1, int x2, int y2, int z2) {
        this.worldName = worldName;
        this.x1 = Math.min(x1, x2);
        this.x2 = Math.max(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.y2 = Math.max(y1, y2);
        this.z1 = Math.min(z1, z2);
        this.z2 = Math.max(z1, z2);
    }

    /**
     * Construct Cuboid с использованием карты со следующими ключами: worldName, x1, x2, y1, y2, z1, z2
     *
     * @param map - карта (ключ значение)
     */
    public Cube(Map<String, Object> map) {
        this.worldName = (String) map.get("worldName");
        this.x1 = (Integer) map.get("x1");
        this.x2 = (Integer) map.get("x2");
        this.y1 = (Integer) map.get("y1");
        this.y2 = (Integer) map.get("y2");
        this.z1 = (Integer) map.get("z1");
        this.z2 = (Integer) map.get("z2");
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("worldName", this.worldName);
        map.put("x1", this.x1);
        map.put("y1", this.y1);
        map.put("z1", this.z1);
        map.put("x2", this.x2);
        map.put("y2", this.y2);
        map.put("z2", this.z2);
        return map;
    }

    /**
     * Получите местоположение нижнего северо-восточного угла кубоида (минимальные координаты XYZ).
     *
     * @return Расположение нижнего северо-восточного угла
     */
    public Location getLowerNE() {
        return new Location(this.getWorld(), this.x1, this.y1, this.z1);
    }

    /**
     * Получите местоположение верхнего юго-западного угла кубоида (максимальные координаты XYZ).
     *
     * @return Расположение верхнего юго-западного угла
     */
    public Location getUpperSW() {
        return new Location(this.getWorld(), this.x2, this.y2, this.z2);
    }

    /**
     * Получить блоки в Cuboid.
     *
     * @return возвращает блоки
     */
    public Collection<Block> getBlocks() {
        Collection<Block> copy = new ArrayList<>();

        this.forEach(block -> {
            if (block.getType() == Material.CHEST) copy.add(block);
        });

        return copy;
    }

    /**
     * Получите центр кубоида.
     *
     * @return Расположение в центре кубоида
     */
    public Location getCenter() {
        int x1 = this.getUpperX() + 1;
        int y1 = this.getUpperY() + 1;
        int z1 = this.getUpperZ() + 1;
        return new Location(this.getWorld(), this.getLowerX() + (x1 - this.getLowerX()) / 2.0, this.getLowerY() + (y1 - this.getLowerY()) / 2.0, this.getLowerZ() + (z1 - this.getLowerZ()) / 2.0);
    }

    /**
     * Получите мир Cuboid.
     *
     * @return Объект World, представляющий мир этого Cuboid
     * @throws IllegalStateException если мир не загружается
     */
    public World getWorld() {
        World world = Bukkit.getWorld(this.worldName);
        //TODO: by zaskamlen optimizing #2
        if (world == null) {
            throw new IllegalStateException("World '" + this.worldName + "' is not loaded");
        }
        return world;
    }

    /**
     * Получите размер этого кубоида по оси X
     *
     * @return возвращает размер
     */
    public int getSizeX() {
        return (this.x2 - this.x1) + 1;
    }

    /**
     * Получите размер этого кубоида по оси Y
     *
     * @return возвращает размер
     */
    public int getSizeY() {
        return (this.y2 - this.y1) + 1;
    }

    /**
     * Получите размер этого кубоида по оси Z
     *
     * @return возвращает размер
     */
    public int getSizeZ() {
        return (this.z2 - this.z1) + 1;
    }

    /**
     * Получите минимальную координату X этого кубоида
     *
     * @return the minimum X co-ordinate
     */
    public int getLowerX() {
        return this.x1;
    }

    /**
     * Получите минимальную координату Y этого кубоида
     *
     * @return the minimum Y co-ordinate
     */
    public int getLowerY() {
        return this.y1;
    }

    /**
     * Получите минимальную координату Z этого кубоида
     *
     * @return the minimum Z co-ordinate
     */
    public int getLowerZ() {
        return this.z1;
    }

    /**
     * Получите максимальную координату X этого кубоида
     *
     * @return the maximum X co-ordinate
     */
    public int getUpperX() {
        return this.x2;
    }

    /**
     * Получите максимальную координату Y этого кубоида
     *
     * @return the maximum Y co-ordinate
     */
    public int getUpperY() {
        return this.y2;
    }

    /**
     * Получите максимальную координату Z этого кубоида
     *
     * @return the maximum Z co-ordinate
     */
    public int getUpperZ() {
        return this.z2;
    }

    /**
     * Получите блоки в восьми углах кубоида.
     *
     * @return массив объектов Block, представляющих углы Cuboid
     */
    public Block[] corners() {
        Block[] res = new Block[8];
        World w = this.getWorld();
        res[0] = w.getBlockAt(this.x1, this.y1, this.z1);
        res[1] = w.getBlockAt(this.x1, this.y1, this.z2);
        res[2] = w.getBlockAt(this.x1, this.y2, this.z1);
        res[3] = w.getBlockAt(this.x1, this.y2, this.z2);
        res[4] = w.getBlockAt(this.x2, this.y1, this.z1);
        res[5] = w.getBlockAt(this.x2, this.y1, this.z2);
        res[6] = w.getBlockAt(this.x2, this.y2, this.z1);
        res[7] = w.getBlockAt(this.x2, this.y2, this.z2);
        return res;
    }

    /**
     * Расширьте кубоид в заданном направлении на заданную величину. Отрицательные величины уменьшат кубоид в заданном направлении. Уменьшение грани прямоугольного параллелепипеда за противоположную грань не является ошибкой и вернет действительный Cub.oid.
     *
     * @param dir    - The direction in which to expand
     * @param amount - The number of blocks by which to expand
     * @return A new Cuboid expanded by the given direction and amount
     */
    public Cube expand(CuboidDirection dir, int amount) {
        switch (dir) {
            case North:
                return new Cube(this.worldName, this.x1 - amount, this.y1, this.z1, this.x2, this.y2, this.z2);
            case South:
                return new Cube(this.worldName, this.x1, this.y1, this.z1, this.x2 + amount, this.y2, this.z2);
            case East:
                return new Cube(this.worldName, this.x1, this.y1, this.z1 - amount, this.x2, this.y2, this.z2);
            case West:
                return new Cube(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y2, this.z2 + amount);
            case Down:
                return new Cube(this.worldName, this.x1, this.y1 - amount, this.z1, this.x2, this.y2, this.z2);
            case Up:
                return new Cube(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y2 + amount, this.z2);
            default:
                throw new IllegalArgumentException("Invalid direction " + dir);
        }
    }

    /**
     * Сдвиньте кубоид в заданном направлении на заданную величину.
     *
     * @param dir    - The direction in which to shift
     * @param amount - The number of blocks by which to shift
     * @return A new Cuboid shifted by the given direction and amount
     */
    public Cube shift(CuboidDirection dir, int amount) {
        return expand(dir, amount).expand(dir.opposite(), -amount);
    }

    /**
     * Начать (вырастить) кубоид в заданном направлении на заданную величину.
     *
     * @param dir    - The direction in which to outset (must be Horizontal, Vertical, or Both)
     * @param amount - The number of blocks by which to outset
     * @return A new Cuboid outset by the given direction and amount
     */
    public Cube outset(CuboidDirection dir, int amount) {
        Cube c;
        switch (dir) {
            case Horizontal:
                c = expand(CuboidDirection.North, amount).expand(CuboidDirection.South, amount).expand(CuboidDirection.East, amount).expand(CuboidDirection.West, amount);
                break;
            case Vertical:
                c = expand(CuboidDirection.Down, amount).expand(CuboidDirection.Up, amount);
                break;
            case Both:
                c = outset(CuboidDirection.Horizontal, amount).outset(CuboidDirection.Vertical, amount);
                break;
            default:
                throw new IllegalArgumentException("Invalid direction " + dir);
        }
        return c;
    }

    /**
     * Inset (shrink) the Cuboid in the given direction by the given amount.  Equivalent
     * to calling outset() with a negative amount.
     *
     * @param dir    - The direction in which to inset (must be Horizontal, Vertical, or Both)
     * @param amount - The number of blocks by which to inset
     * @return A new Cuboid inset by the given direction and amount
     */
    public Cube inset(CuboidDirection dir, int amount) {
        return this.outset(dir, -amount);
    }

    /**
     * Return true if the point at (x,y,z) is contained within this Cuboid.
     *
     * @param x - The X co-ordinate
     * @param y - The Y co-ordinate
     * @param z - The Z co-ordinate
     * @return true if the given point is within this Cuboid, false otherwise
     */
    public boolean contains(int x, int y, int z) {
        return x >= this.x1 && x <= this.x2 && y >= this.y1 && y <= this.y2 && z >= this.z1 && z <= this.z2;
    }

    /**
     * Проверьте, содержится ли данный блок в этом кубоиде.
     *
     * @param b - Блок для проверки
     * @return true, если блок находится внутри этого кубоида, иначе false
     */
    public boolean contains(Block b) {
        return this.contains(b.getLocation());
    }

    /**
     * Проверьте, содержится ли данное местоположение в этом кубоиде.
     *
     * @param l - Место для проверки
     * @return true если Location находится внутри этого Cuboid, в противном случае false
     */
    public boolean contains(Location l) {
        //TODO::by zaskamlen optimizing #3
        // if (!this.worldName.equals(l.getWorld().getName())) {
        // return false;
        //}
        //return this.contains(l.getBlockX(), l.getBlockY(), l.getBlockZ());
        return this.worldName.equals(l.getWorld().getName()) && this.contains(l.getBlockX(), l.getBlockY(), l.getBlockZ());
    }

    /**
     * Получите объем этого кубоида.
     *
     * @return The Cuboid volume, in blocks
     */
    public int getVolume() {
        return this.getSizeX() * this.getSizeY() * this.getSizeZ();
    }

    /**
     * Получите средний уровень освещенности всех пустых (воздушных) блоков кубоида. Возвращает 0, если нет пустых блоков.
     *
     * @return Средний уровень освещенности этого кубоида
     */
    public byte getAverageLightLevel() {
        long total = 0;
        int n = 0;
        for (Block b : this) {
            if (b.isEmpty()) {
                total += b.getLightLevel();
                ++n;
            }
        }
        return n > 0 ? (byte) (total / n) : 0;
    }

    /**
     * Сократите Кубоид, вернув Кубоид с удаленным воздухом по краям, достаточно большой, чтобы включить все невоздушные блоки.
     *
     * @return Новый Cuboid без внешних воздушных блоков
     */
    public Cube contract() {
        return this.contract(CuboidDirection.Down).contract(CuboidDirection.South).contract(CuboidDirection.East).contract(CuboidDirection.Up).contract(CuboidDirection.North).contract(CuboidDirection.West);
    }

    /**
     * Сожмите кубоид в заданном направлении, возвращая новый кубоид, у которого нет внешнего пустого пространства.
     * Напр. Направление Вниз максимально опустит верхнюю грань вниз.
     *
     * @param dir - Направление, в котором заключать контракт
     * @return Новый кубоид сжался в заданном направлении
     */

    //TODO:Переписано
    public Cube contract(CuboidDirection dir) {
        Cube face = getFace(dir.opposite());
        switch (dir) {
            case Down:
                contractDown(face);
            case Up:
                contractUp(face);
            case North:
                contractNorth(face);
            case South:
                contractSouth(face);
            case East:
                contractEast(face);
            case West:
                contractWest(face);
            default:
                throw new IllegalArgumentException("Invalid direction " + dir);
        }
    }

    //TODO: by zaskamlen add
    private Cube contractDown(Cube face) {
        while ((face.containsOnly(0)) && (face.getLowerY() > this.getLowerY())) {
            face = face.shift(CuboidDirection.Down, 1);
        }
        return new Cube(this.worldName, this.x1, this.y1, this.z1, this.x2, face.getUpperY(), this.z2);
    }

    private Cube contractUp(Cube face) {
        while ((face.containsOnly(0)) && (face.getUpperY() < this.getUpperY())) {
            face = face.shift(CuboidDirection.Up, 1);
        }
        return new Cube(this.worldName, this.x1, face.getLowerY(), this.z1, this.x2, this.y2, this.z2);
    }

    private Cube contractNorth(Cube face) {
        while ((face.containsOnly(0)) && (face.getLowerX() > this.getLowerX())) {
            face = face.shift(CuboidDirection.North, 1);
        }
        return new Cube(this.worldName, this.x1, this.y1, this.z1, face.getUpperX(), this.y2, this.z2);
    }

    private Cube contractSouth(Cube face) {
        while ((face.containsOnly(0)) && (face.getUpperX() < this.getUpperX())) {
            face = face.shift(CuboidDirection.South, 1);
        }
        return new Cube(this.worldName, face.getLowerX(), this.y1, this.z1, this.x2, this.y2, this.z2);
    }

    private Cube contractEast(Cube face) {
        while ((face.containsOnly(0)) && (face.getLowerZ()) > (this.getLowerZ())) {
            face = face.shift(CuboidDirection.East, 1);
        }
        return new Cube(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y2, face.getUpperZ());
    }

    private Cube contractWest(Cube face) {
        while ((face.containsOnly(0)) && (face.getUpperZ()) < (this.getUpperZ())) {
            face = face.shift(CuboidDirection.West, 1);
        }
        return new Cube(this.worldName, this.x1, this.y1, face.getLowerZ(), this.x2, this.y2, this.z2);
    }

    /**
     * Получите Кубоид, представляющий лицо этого Кубоида. Результирующий кубоид будет иметь толщину в один блок по оси, перпендикулярной запрошенной грани.
     *
     * @param dir - какую грань кубоида получить
     * @return Кубоид, представляющий запрошенное лицо этого кубоида
     */
    public Cube getFace(CuboidDirection dir) {
        switch (dir) {
            case Down:
                return new Cube(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y1, this.z2);
            case Up:
                return new Cube(this.worldName, this.x1, this.y2, this.z1, this.x2, this.y2, this.z2);
            case North:
                return new Cube(this.worldName, this.x1, this.y1, this.z1, this.x1, this.y2, this.z2);
            case South:
                return new Cube(this.worldName, this.x2, this.y1, this.z1, this.x2, this.y2, this.z2);
            case East:
                return new Cube(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y2, this.z1);
            case West:
                return new Cube(this.worldName, this.x1, this.y1, this.z2, this.x2, this.y2, this.z2);
            default:
                throw new IllegalArgumentException("Invalid direction " + dir);
        }
    }

    /**
     * Проверьте, содержит ли Cuboid только блоки данного типа
     *
     * @param blockId - Идентификатор блока для проверки
     * @return true если этот Cuboid содержит только блоки данного типаv
     */
    public boolean containsOnly(int blockId) {
        for (Block b : this) {
            //TODO:by zaskamlen optimizing #4
            if (b.getTypeId() != blockId) {
                return false;
            }
        }
        return true;
    }

    /**
     * Сделайте Кубоид достаточно большим, чтобы вместить как этот Кубоид, так и данный.
     *
     * @param other -Другой кубовид.
     * @return Новый кубоид, достаточно большой, чтобы вместить этот кубоид и данный кубоид
     */
    public Cube getBoundingCuboid(Cube other) {
        //TODO:by zaskamlen optimizing #5
        if (other == null) {
            return this;
        }

        int xMin = Math.min(this.getLowerX(), other.getLowerX());
        int yMin = Math.min(this.getLowerY(), other.getLowerY());
        int zMin = Math.min(this.getLowerZ(), other.getLowerZ());
        int xMax = Math.max(this.getUpperX(), other.getUpperX());
        int yMax = Math.max(this.getUpperY(), other.getUpperY());
        int zMax = Math.max(this.getUpperZ(), other.getUpperZ());

        return new Cube(this.worldName, xMin, yMin, zMin, xMax, yMax, zMax);
    }

    /**
     * Получите блок относительно нижней северо-восточной точки кубоида.
     *
     * @param x - The X co-ordinate
     * @param y - The Y co-ordinate
     * @param z - The Z co-ordinate
     * @return The block at the given position
     */
    public Block getRelativeBlock(int x, int y, int z) {
        return this.getWorld().getBlockAt(this.x1 + x, this.y1 + y, this.z1 + z);
    }

    /**
     * Получить блок относительно нижней северо-восточной точки кубоида в данном мире. Этот
     * следует использовать версию getRelativeBlock(), если она вызывается много раз, чтобы избежать
     * чрезмерные вызовы getWorld().
     *
     * @param w - The world
     * @param x - The X co-ordinate
     * @param y - The Y co-ordinate
     * @param z - The Z co-ordinate
     * @return The block at the given position
     */
    public Block getRelativeBlock(World w, int x, int y, int z) {
        return w.getBlockAt(this.x1 + x, y1 + y, this.z1 + z);
    }

    /**
     * Получите список фрагментов, которые полностью или частично содержатся в этом прямоугольном параллелепипеде.
     *
     * @return A list of Chunk objects
     */
    public List<Chunk> getChunks() {
        List<Chunk> res = new ArrayList<Chunk>();

        World w = this.getWorld();
        int x1 = this.getLowerX() & ~0xf;
        int x2 = this.getUpperX() & ~0xf;
        int z1 = this.getLowerZ() & ~0xf;
        int z2 = this.getUpperZ() & ~0xf;
        for (int x = x1; x <= x2; x += 16) {
            for (int z = z1; z <= z2; z += 16) {
                res.add(w.getChunkAt(x >> 4, z >> 4));
            }
        }
        return res;
    }

    public Iterator<Block> iterator() {
        return new CuboidIterator(this.getWorld(), this.x1, this.y1, this.z1, this.x2, this.y2, this.z2);
    }

    @Override
    public Cube clone() {
        return new Cube(this);
    }

    @Override
    public String toString() {
        return "Cuboid: " + this.worldName + "," + this.x1 + "," + this.y1 + "," + this.z1 + "=>" + this.x2 + "," + this.y2 + "," + this.z2;
    }

    public enum CuboidDirection {
        North, East, South, West, Up, Down, Horizontal, Vertical, Both, Unknown;

        public CuboidDirection opposite() {
            switch (this) {
                case North:
                    return South;
                case East:
                    return West;
                case South:
                    return North;
                case West:
                    return East;
                case Horizontal:
                    return Vertical;
                case Vertical:
                    return Horizontal;
                case Up:
                    return Down;
                case Down:
                    return Up;
                case Both:
                    return Both;
                default:
                    return Unknown;
            }
        }

    }

    public class CuboidIterator implements Iterator<Block> {

        private final World w;
        private final int baseX;
        private final int baseY;
        private final int baseZ;
        private final int sizeX;
        private final int sizeY;
        private final int sizeZ;
        private int x, y, z;

        public CuboidIterator(World w, int x1, int y1, int z1, int x2, int y2, int z2) {
            this.w = w;
            this.baseX = x1;
            this.baseY = y1;
            this.baseZ = z1;
            this.sizeX = Math.abs(x2 - x1) + 1;
            this.sizeY = Math.abs(y2 - y1) + 1;
            this.sizeZ = Math.abs(z2 - z1) + 1;
            this.x = this.y = this.z = 0;
        }

        public boolean hasNext() {
            return this.x < this.sizeX && this.y < this.sizeY && this.z < this.sizeZ;
        }

        public Block next() {
            Block b = this.w.getBlockAt(this.baseX + this.x, this.baseY + this.y, this.baseZ + this.z);
            if (++x >= this.sizeX) {
                this.x = 0;
                if (++this.y >= this.sizeY) {
                    this.y = 0;
                    ++this.z;
                }
            }

            return b;
        }

        public void remove() {
        }
    }
}

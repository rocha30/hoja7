//no puede Poner maven en ese proyecto, lo hice desde una computadora diferente y no me dejo iniciar un proyecto con maven pero creo que asi quedarian los Test 

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class Test {

    private BinaryTree<Association<String, String>> tree;

    @Before
    public void setUp() {
        // Asegúrate de inicializar el árbol con un valor no nulo.
        tree = new BinaryTree<>(new Association<>("house", "casa"));
    }

    @Test
    public void testInsertAndFind() {
        Association<String, String> toInsert = new Association<>("dog", "perro");
        tree.insert(toInsert);
        assertEquals(toInsert, tree.find(toInsert));
    }

    @Test
    public void testFindNonExistent() {
        Association<String, String> toFind = new Association<>("cat", null);
        assertNull(tree.find(toFind));
    }

    @Test
    public void testGetKey() {
        Association<String, String> association = new Association<>("house", "casa");
        assertEquals("The key should be 'house'.", "house", association.getKey());
    }

    @Test
    public void testGetValue() {
        Association<String, String> association = new Association<>("dog", "perro");
        assertEquals("The value should be 'perro'.", "perro", association.getValue());
    }



}
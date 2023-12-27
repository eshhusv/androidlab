import com.example.myapplication.MainActivity
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun testExitButton() {
        val ma = MainActivity()
        ma.editText.setText("ruslan varfolomeevich")
        ma.reverse.performClick()
        assertEquals("hciveemolofrav nalsur", ma.editText.text)
    }
}
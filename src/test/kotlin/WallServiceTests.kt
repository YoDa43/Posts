import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun add() {
        // создаём целевой сервис
        val service = WallService
        // заполняем несколькими постами
        service.add(Post(1, 1, 1, 1, "Поздравляем Вас с днем рождения", 1, true, true, true, true, Reposts(10, true, 1), Answer(1, 1)))
        service.add(Post(2, 1, 1, 2, "Новый год", 1, true, true, true, true, Reposts(10, true, 2), Answer(1, 1)))
        // проверяем результат (используйте assertTrue или assertFalse)
        assertTrue(service.posts.isNotEmpty())
    }

    @Test
    fun updateExisting() {
        // создаём целевой сервис
        val service = WallService
        // заполняем несколькими постами
        service.add(Post(1, 1, 1, 1, "Поздравляем Вас с днем рождения", 1, true, true, true, true, Reposts(10, true, 1), Answer(1, 1)))
        service.add(Post(2, 1, 1, 2, "Новый год", 1, true, true, true, true, Reposts(10, true, 2), Answer(1, 1)))
        // создаём информацию об обновлении
        val update = Post(2, 1, 1, 2, "Новый год", 5, true, true, true, true, Reposts(10, true, 2), Answer(3, 1))

        // выполняем целевое действие
        val result = service.update(update)
        assertTrue(result) // true, если пост обновлен
    }
}
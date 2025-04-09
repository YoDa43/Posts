data class Answer(val id:Int,val date:Int)
data class Reposts(val count:Int,val userReposted:Boolean,val date:Int)

data class Post(
    val id: Int,  //Идентификатор записи.
    val ownerId: Int,  //Идентификатор владельца стены.
    val fromId: Int,  //Идентификатор автора записи (от чьего имени опубликована запись).
    val date: Int,  //Время публикации записи в формате unixtime.
    val text: String,  //Текст записи.
    val likes: Int,  //Количество лайков.
    val canPin: Boolean,  //Информация о том, может ли текущий пользователь закрепить запись. Возможные значения: 1 — может, 0 — не может.
    val canDelete: Boolean,  //Информация о том, может ли текущий пользователь удалить запись. Возможные значения: 1 — может, 0 — не может.
    val canEdit: Boolean,  //Информация о том, может ли текущий пользователь редактировать запись. Возможные значения: 1 — может, 0 — не может.
    val isPinned: Boolean,  //Информация о том, прикреплена ли запись. Возможные значения: 1 — запись прикреплена, 0 — запись не прикреплена.
    val reposts: Reposts,
    val answer: Answer
)

object WallService {
    var posts = emptyArray<Post>()
    fun clear() {
        posts = emptyArray<Post>()
        count = 0
    }
    private var count = 0
    fun add(post: Post): Post {
        posts += post.copy(id = ++count)
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, p) in posts.withIndex()) {
            if (p.id == post.id) {
                posts[index] = post
                return true
            }
        }
        return false
    }

    fun printAllPosts() {
        posts.forEach {
            println(it)
        }
    }
}

fun main() {
    WallService.add(Post(1, 1, 1, 1, "Поздравляем Вас с днем рождения", 1, true, true, true, true, Reposts(10, true, 1), Answer(1, 1)))
    WallService.add(Post(1, 1, 1, 2, "Новый год", 1, true, true, true, true, Reposts(10, true, 2), Answer(1, 1)))
    WallService.update(Post(3, 1, 1, 2, "Новый год", 5, true, true, true, true, Reposts(10, true, 2), Answer(3, 1)))
    WallService.printAllPosts()
}



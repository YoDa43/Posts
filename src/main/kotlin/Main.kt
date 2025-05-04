data class Answer(val id:Int,val date:Int)
data class Reposts(val count:Int,val userReposted:Boolean,val date:Int)

data class Post(
    val id: Int,  //Идентификатор записи.
    val ownerId: Int,  //Идентификатор владельца стены.
    val fromId: Int,  //Идентификатор автора записи (от чьего имени опубликована запись).
    val date: Int,  //Время публикации записи в формате unixtime.
    val text: String,  //Текст записи.
    var likes: Int?,  //Количество лайков.
    val canPin: Boolean,  //Информация о том, может ли текущий пользователь закрепить запись. Возможные значения: 1 — может, 0 — не может.
    val canDelete: Boolean,  //Информация о том, может ли текущий пользователь удалить запись. Возможные значения: 1 — может, 0 — не может.
    val canEdit: Boolean,  //Информация о том, может ли текущий пользователь редактировать запись. Возможные значения: 1 — может, 0 — не может.
    val isPinned: Boolean,  //Информация о том, прикреплена ли запись. Возможные значения: 1 — запись прикреплена, 0 — запись не прикреплена.
    val reposts: Reposts,
    val answer: Answer,
    val attachments: List<Any> = listOf<Attachment>()
)
object WallService {
    var posts = emptyArray<Post>()
    fun clear() {
        posts = emptyArray<Post>()
        count = 0
    }
    private var count = 0
    fun add(post: Post): Post {
        if (post.likes == null) {
            post.likes = 0
        }
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

data class Attachment(val type: String)
data class Photo(val id: Int,val ownerId: Int,val photo130: String,val photo604: String)
data class PhotoAttachments(val photo: Photo)
data class Video(val id: Int,val ownerId: Int,val title: String,val duration: Int)
data class VideoAttachments(val video: Video)

fun main() {
    WallService.clear()
    WallService.add(Post(1, 1, 1, 1, "Поздравляем Вас с днем рождения", null, true, true, true, true, Reposts(10, true, 1), Answer(1, 1), listOf(PhotoAttachments(Photo(1,1,"https://vk.com/some_photo_link","https://vk.com/another_photo_link")), VideoAttachments(Video(1,1,"A Funny Video",30)))))
    WallService.add(Post(1, 1, 1, 2, "Новый год", null, true, true, true, true, Reposts(10, true, 2), Answer(1, 1)))
    WallService.update(Post(3, 1, 1, 2, "Новый год", null, true, true, true, true, Reposts(10, true, 2), Answer(3, 1)))
    WallService.printAllPosts()
}
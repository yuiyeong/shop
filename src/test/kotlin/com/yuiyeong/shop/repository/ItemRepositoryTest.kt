package com.yuiyeong.shop.repository

import com.yuiyeong.shop.domain.item.Album
import com.yuiyeong.shop.domain.item.Book
import com.yuiyeong.shop.domain.item.Movie
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest
@Transactional
class ItemRepositoryTest(@Autowired private val itemRepository: ItemRepository) {
    @Test
    fun testSavingItem() {
        // given
        val book = Book("test author", "test isbn", "test book name", 1000, 100)
        val album = Album("test artist", "test etc", "test album name", 2000, 100)
        val movie = Movie("test director", "test actor", "test movie name", 2000, 100)

        // when
        val savedBookId = itemRepository.save(book)
        val savedAlbumId = itemRepository.save(album)
        val savedMovieId = itemRepository.save(movie)

        // then
        val foundBook = itemRepository.findItem(savedBookId)
        assertEquals(book, foundBook)

        val foundAlbum = itemRepository.findItem(savedAlbumId)
        assertEquals(album, foundAlbum)

        val foundMovie = itemRepository.findItem(savedMovieId)
        assertEquals(movie, foundMovie)
    }

    @Test
    fun testFindAll() {
        // given
        val book = Book("test author", "test isbn", "test book name", 1000, 100)
        val album = Album("test artist", "test etc", "test album name", 2000, 100)
        val movie = Movie("test director", "test actor", "test movie name", 2000, 100)
        itemRepository.save(book)
        itemRepository.save(album)
        itemRepository.save(movie)

        // when
        val items = itemRepository.findAll()

        // then
        assertEquals(3, items.count())
        assertEquals(book, items[0])
        assertEquals(album, items[1])
        assertEquals(movie, items[2])
    }

    @Test
    fun testFindItem() {
        // given
        val book = Book("test author", "test isbn", "test book name", 1000, 100)
        val savedBookId = itemRepository.save(book)

        //when
        val foundItem = itemRepository.findItem(savedBookId)

        // then
        assertEquals(book, foundItem)
    }
}
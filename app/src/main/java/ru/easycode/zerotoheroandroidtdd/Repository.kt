package ru.easycode.zerotoheroandroidtdd

interface Repository {

    interface Read {
        fun list(): List<Item>
    }

    interface Add {
        fun add(value: String): Long
    }

    interface DeleteItem {
        fun delete(id: Long)
    }

    interface ReadItem {
        fun item(id: Long): Item
    }

    interface Mutable : Read, Add

    interface Delete: ReadItem, DeleteItem

    interface All: Mutable, Delete

    class Base(
        private val dataSource: ItemsDao,
        private val now: Now
    ) : All {
        override fun list(): List<Item> {
            return dataSource.list().map { Item(id = it.id, text = it.text) }
        }

        override fun add(value: String): Long {
            val id = now.nowMillis()
            dataSource.add(ItemCache(id, value))
            return id
        }

        override fun delete(id: Long) {
            dataSource.delete(id)
        }

        override fun item(id: Long): Item {
            val itemCache = dataSource.item(id)
            return Item(id = itemCache.id, text = itemCache.text)
        }

    }

}

data class Item(val id: Long, val text: String)
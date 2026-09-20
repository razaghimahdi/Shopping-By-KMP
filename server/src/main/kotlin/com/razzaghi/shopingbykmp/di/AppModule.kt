package com.razzaghi.shopingbykmp.di

import com.razzaghi.shopingbykmp.repository.AddressRepository
import com.razzaghi.shopingbykmp.repository.AddressRepositoryImpl
import com.razzaghi.shopingbykmp.repository.AuthRepository
import com.razzaghi.shopingbykmp.repository.AuthRepositoryImpl
import com.razzaghi.shopingbykmp.repository.BasketRepository
import com.razzaghi.shopingbykmp.repository.BasketRepositoryImpl
import com.razzaghi.shopingbykmp.repository.CatalogRepository
import com.razzaghi.shopingbykmp.repository.CatalogRepositoryImpl
import com.razzaghi.shopingbykmp.repository.CommentRepository
import com.razzaghi.shopingbykmp.repository.CommentRepositoryImpl
import com.razzaghi.shopingbykmp.repository.NotificationRepository
import com.razzaghi.shopingbykmp.repository.NotificationRepositoryImpl
import com.razzaghi.shopingbykmp.repository.OrderRepository
import com.razzaghi.shopingbykmp.repository.OrderRepositoryImpl
import com.razzaghi.shopingbykmp.repository.ProfileRepository
import com.razzaghi.shopingbykmp.repository.ProfileRepositoryImpl
import com.razzaghi.shopingbykmp.repository.SearchRepository
import com.razzaghi.shopingbykmp.repository.SearchRepositoryImpl
import com.razzaghi.shopingbykmp.repository.WishlistRepository
import com.razzaghi.shopingbykmp.repository.WishlistRepositoryImpl
import com.razzaghi.shopingbykmp.security.TokenManager
import org.koin.dsl.module

val appModule = module {
    single<AuthRepository> { AuthRepositoryImpl() }
    single { TokenManager() }
    single<OrderRepository> { OrderRepositoryImpl() }
    single<NotificationRepository> { NotificationRepositoryImpl() }
    single<AddressRepository> { AddressRepositoryImpl() }
    single<ProfileRepository> { ProfileRepositoryImpl() }
    single<BasketRepository> { BasketRepositoryImpl() }
    single<CatalogRepository> { CatalogRepositoryImpl() }
    single<SearchRepository> { SearchRepositoryImpl() }
    single<WishlistRepository> { WishlistRepositoryImpl() }
    single<CommentRepository> { CommentRepositoryImpl() }
}
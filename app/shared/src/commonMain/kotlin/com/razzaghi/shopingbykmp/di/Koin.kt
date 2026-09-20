package com.razzaghi.shopingbykmp.di


import com.razzaghi.shopingbykmp.business.core.KtorHttpClient
import com.razzaghi.shopingbykmp.business.datasource.network.splash.SplashService
import com.razzaghi.shopingbykmp.business.datasource.network.splash.SplashServiceImpl
import com.razzaghi.shopingbykmp.business.interactors.main.AddAddressUseCase
import com.razzaghi.shopingbykmp.business.interactors.main.AddBasketUseCase
import com.razzaghi.shopingbykmp.business.interactors.main.AddCommentUseCase
import com.razzaghi.shopingbykmp.business.interactors.main.BasketListUseCase
import com.razzaghi.shopingbykmp.business.interactors.main.BuyProductUseCase
import com.razzaghi.shopingbykmp.business.interactors.main.DeleteBasketUseCase
import com.razzaghi.shopingbykmp.business.interactors.main.GetAddressesUseCase
import com.razzaghi.shopingbykmp.business.interactors.main.GetCommentsUseCase
import com.razzaghi.shopingbykmp.business.interactors.main.GetEmailFromCacheUseCase
import com.razzaghi.shopingbykmp.business.interactors.main.GetNotificationsUseCase
import com.razzaghi.shopingbykmp.business.interactors.main.GetOrdersUseCase
import com.razzaghi.shopingbykmp.business.interactors.main.GetProfileUseCase
import com.razzaghi.shopingbykmp.business.interactors.main.GetSearchFilterUseCase
import com.razzaghi.shopingbykmp.business.interactors.main.HomeUseCase
import com.razzaghi.shopingbykmp.business.interactors.main.LikeUseCase
import com.razzaghi.shopingbykmp.business.interactors.main.LogoutUseCase
import com.razzaghi.shopingbykmp.business.interactors.main.ProductUseCase
import com.razzaghi.shopingbykmp.business.interactors.main.SearchUseCase
import com.razzaghi.shopingbykmp.business.interactors.main.UpdateProfileUseCase
import com.razzaghi.shopingbykmp.business.interactors.main.WishListUseCase
import com.razzaghi.shopingbykmp.business.interactors.splash.CheckTokenUseCase
import com.razzaghi.shopingbykmp.business.interactors.splash.LoginUseCase
import com.razzaghi.shopingbykmp.business.interactors.splash.RegisterUseCase
import com.razzaghi.shopingbykmp.business.datasource.network.main.MainService
import com.razzaghi.shopingbykmp.business.datasource.network.main.MainServiceImpl
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import com.razzaghi.shopingbykmp.presentation.SharedViewModel
import com.razzaghi.shopingbykmp.presentation.token_manager.TokenManager
import com.razzaghi.shopingbykmp.presentation.ui.main.add_address.view_model.AddAddressViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.address.view_model.AddressViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.cart.view_model.CartViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.categories.view_model.CategoriesViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.checkout.view_model.CheckoutViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.comment.view_model.CommentViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.detail.view_model.DetailViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.edit_profile.view_model.EditProfileViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.home.view_model.HomeViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.my_coupons.view_model.MyCouponsViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.my_orders.view_model.MyOrdersViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.notifications.view_model.NotificationsViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.payment_method.view_model.PaymentMethodViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.profile.view_model.ProfileViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.search.view_model.SearchViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.settings.view_model.SettingsViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.wishlist.view_model.WishlistViewModel
import com.razzaghi.shopingbykmp.presentation.ui.splash.view_model.LoginViewModel
import org.koin.core.module.dsl.viewModel


fun appModule() = module {

    includes(dataStoreModule())

    single { Json { isLenient = true; ignoreUnknownKeys = true } }
    single {
        KtorHttpClient.httpClient(get())
    }
    single<SplashService> { SplashServiceImpl(get()) }
    single<MainService> { MainServiceImpl(get()) }


    viewModel { SharedViewModel(get()) }
    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { AddressViewModel(get()) }
    viewModel { AddAddressViewModel(get()) }
    viewModel { CategoriesViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { EditProfileViewModel(get(), get(), get()) }
    viewModel { PaymentMethodViewModel() }
    viewModel { NotificationsViewModel(get()) }
    viewModel { MyCouponsViewModel() }
    viewModel { MyOrdersViewModel(get()) }
    viewModel { CheckoutViewModel(get(), get(), get()) }
    viewModel { WishlistViewModel(get(), get()) }
    viewModel { CartViewModel(get(), get(), get()) }
    viewModel { DetailViewModel(get(), get(), get()) }
    viewModel { SearchViewModel(get(), get()) }
    viewModel { CommentViewModel(get(), get()) }


    single { WishListUseCase(get(), get()) }
    single { BasketListUseCase(get(), get()) }
    single { GetProfileUseCase(get(), get()) }
    single { UpdateProfileUseCase(get(), get()) }
    single { TokenManager(get(), get()) }
    single { LogoutUseCase(get()) }
    single { GetEmailFromCacheUseCase(get()) }
    single { GetSearchFilterUseCase(get(), get()) }
    single { SearchUseCase(get(), get()) }
    single { AddCommentUseCase(get(), get()) }
    single { BuyProductUseCase(get(), get()) }
    single { GetCommentsUseCase(get(), get()) }
    single { GetAddressesUseCase(get(), get()) }
    single { GetOrdersUseCase(get(), get()) }
    single { GetNotificationsUseCase(get(), get()) }
    single { AddAddressUseCase(get(), get()) }
    single { AddBasketUseCase(get(), get()) }
    single { DeleteBasketUseCase(get(), get()) }
    single { LikeUseCase(get(), get()) }
    single { LoginUseCase(get(), get()) }
    single { RegisterUseCase(get(), get()) }
    single { CheckTokenUseCase(get()) }
    single { HomeUseCase(get(), get()) }
    single { ProductUseCase(get(), get()) }
}
package di


import business.core.AppDataStore
import business.core.AppDataStoreManager
import business.core.KtorHttpClient
import business.datasource.network.main.MainService
import business.datasource.network.main.MainServiceImpl
import business.datasource.network.splash.SplashService
import business.datasource.network.splash.SplashServiceImpl
import business.interactors.main.AddAddressInteractor
import business.interactors.main.AddBasketInteractor
import business.interactors.main.AddCommentInteractor
import business.interactors.main.BasketListInteractor
import business.interactors.main.BuyProductInteractor
import business.interactors.main.DeleteBasketInteractor
import business.interactors.main.GetAddressesInteractor
import business.interactors.main.GetCommentsInteractor
import business.interactors.main.GetEmailFromCacheInteractor
import business.interactors.main.GetOrdersInteractor
import business.interactors.main.GetProfileInteractor
import business.interactors.main.GetSearchFilterInteractor
import business.interactors.main.HomeInteractor
import business.interactors.main.LikeInteractor
import business.interactors.main.LogoutInteractor
import business.interactors.main.ProductInteractor
import business.interactors.main.SearchInteractor
import business.interactors.main.UpdateProfileInteractor
import business.interactors.main.WishListInteractor
import business.interactors.splash.CheckTokenInteractor
import business.interactors.splash.LoginInteractor
import business.interactors.splash.RegisterInteractor
import common.Context
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import presentation.SharedViewModel
import presentation.token_manager.TokenManager
import presentation.ui.main.address.view_model.AddressViewModel
import presentation.ui.main.cart.view_model.CartViewModel
import presentation.ui.main.categories.view_model.CategoriesViewModel
import presentation.ui.main.checkout.view_model.CheckoutViewModel
import presentation.ui.main.comment.view_model.CommentViewModel
import presentation.ui.main.detail.view_model.DetailViewModel
import presentation.ui.main.edit_profile.view_model.EditProfileViewModel
import presentation.ui.main.home.view_model.HomeViewModel
import presentation.ui.main.my_coupons.view_model.MyCouponsViewModel
import presentation.ui.main.my_orders.view_model.MyOrdersViewModel
import presentation.ui.main.notifications.view_model.NotificationsViewModel
import presentation.ui.main.payment_method.view_model.PaymentMethodViewModel
import presentation.ui.main.profile.view_model.ProfileViewModel
import presentation.ui.main.search.view_model.SearchViewModel
import presentation.ui.main.settings.view_model.SettingsViewModel
import presentation.ui.main.wishlist.view_model.WishlistViewModel
import presentation.ui.splash.view_model.LoginViewModel


fun appModule(context: Context) = module {
    single { Json { isLenient = true; ignoreUnknownKeys = true } }
    single {
        KtorHttpClient.httpClient(get())
    }
    single<SplashService> { SplashServiceImpl(get()) }
    single<MainService> { MainServiceImpl(get()) }
    single<AppDataStore> { AppDataStoreManager(context) }
    factory { SharedViewModel(get()) }
    factory { LoginViewModel(get(), get(), get()) }
    factory { HomeViewModel(get(), get()) }
    factory { AddressViewModel(get(), get()) }
    factory { CategoriesViewModel(get()) }
    factory { ProfileViewModel(get()) }
    factory { SettingsViewModel(get()) }
    factory { EditProfileViewModel(get(), get(), get()) }
    factory { PaymentMethodViewModel() }
    factory { NotificationsViewModel() }
    factory { MyCouponsViewModel() }
    factory { MyOrdersViewModel(get()) }
    factory { CheckoutViewModel(get(), get(), get()) }
    factory { WishlistViewModel(get(), get()) }
    factory { CartViewModel(get(), get(), get()) }
    factory { DetailViewModel(get(), get(), get()) }
    factory { SearchViewModel(get(), get()) }
    single { WishListInteractor(get(), get()) }
    single { BasketListInteractor(get(), get()) }
    single { GetProfileInteractor(get(), get()) }
    single { UpdateProfileInteractor(get(), get()) }
    single { TokenManager(get(), get()) }
    single { LogoutInteractor(get()) }
    single { GetEmailFromCacheInteractor(get()) }
    single { GetSearchFilterInteractor(get(), get()) }
    single { SearchInteractor(get(), get()) }
    single { AddCommentInteractor(get(), get()) }
    single { BuyProductInteractor(get(), get()) }
    single { CommentViewModel(get(), get()) }
    single { GetCommentsInteractor(get(), get()) }
    single { GetAddressesInteractor(get(), get()) }
    single { GetOrdersInteractor(get(), get()) }
    single { AddAddressInteractor(get(), get()) }
    single { AddBasketInteractor(get(), get()) }
    single { DeleteBasketInteractor(get(), get()) }
    single { LikeInteractor(get(), get()) }
    single { LoginInteractor(get(), get()) }
    single { RegisterInteractor(get(), get()) }
    single { CheckTokenInteractor(get()) }
    single { HomeInteractor(get(), get()) }
    single { ProductInteractor(get(), get()) }
}
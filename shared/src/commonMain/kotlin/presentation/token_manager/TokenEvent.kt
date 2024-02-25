package presentation.token_manager

sealed class TokenEvent {
    data object CheckToken : TokenEvent()
    data object Logout : TokenEvent()

}
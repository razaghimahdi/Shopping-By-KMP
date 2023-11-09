package business.core

/**
 * A generic class for hiding/showing some ui component.
 */
sealed class UIComponentState {

    object Show: UIComponentState()

    object HalfShow: UIComponentState()

    object Hide: UIComponentState()
}

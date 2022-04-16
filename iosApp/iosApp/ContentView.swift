import SwiftUI
import shared

struct ContentView: View {
    @EnvironmentObject var appObj: AppObservableObject
	let greet = Greeting().greeting()

	var body: some View {
        let screenState = appObj.nav.stateProvider.getToCast(screenIdentifier: ScreenIdentifier(screen: .list, params: nil)) as! CountriesListState
		Text(greet + "\(screenState.countriesListItems)")
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

//
//  BottomBar.swift
//  iosApp
//
//  Created by Zhaolong Zhong on 4/18/22.
//

import SwiftUI

@ViewBuilder func bottomBar(selectedTab: ScreenState, onTap: @escaping ((ScreenState) -> Void)) -> some View {
    Spacer()
    // iconName:
    // https://developer.apple.com/design/human-interface-guidelines/macos/icons-and-images/system-images/
    // https://developer.apple.com/sf-symbols/
    BottomBarButton(
        itemLabel: "Home",
        iconName: "house",
        selected: selectedTab == .home,
        onClick: { onTap(.home) }
    )
    Spacer()
    BottomBarButton(
        itemLabel: "Me",
        iconName: "person",
        selected: selectedTab == .me,
        onClick: {
            onTap(.me)
        }
    )
    Spacer()
    BottomBarButton(
        itemLabel: "Debug",
        iconName: "gearshape",
        selected: selectedTab == .debugSettings,
        onClick: {
            onTap(.debugSettings)
        }
    )
    Spacer()
}

struct BottomBarButton: View {
    var itemLabel : String
    var iconName : String
    var selected : Bool
    var onClick : () -> Void
    
    var body: some View {
        Button(action: { onClick() }) {
            VStack(spacing: 5) {
                Image(systemName: iconName).resizable().scaledToFit().frame(height:15)
                Text(itemLabel).font(Font.footnote)
            }
            .foregroundColor(selected ? .orange : .gray)
        }
    }
}

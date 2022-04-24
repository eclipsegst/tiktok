//
//  SignUpScreen.swift
//  iosApp
//
//  Created by Zhaolong Zhong on 4/23/22.
//

import SwiftUI
import shared

class SignUpObservableObject: ObservableObject {
    @Published var username = ""
    @Published var password = ""
    @Published var confirmPassword = ""
}

struct SignUpScreen: View {
    @ObservedObject var observedObject = SignUpObservableObject()
        
    var body: some View{
    
        VStack{
            Text("App Name")
                .font(.title)
                .fontWeight(.bold)
                .padding(.bottom, 200)
        
            RectangleTextField(fieldHint: "Username or Email", nameOfField: $observedObject.username)
            
            PasswordField(fieldHint: "Password", nameOfField: $observedObject.password)
            
            PasswordField(fieldHint: "Confirm Password", nameOfField: $observedObject.confirmPassword)
            
            Button(action: {
                self.register()
            }) {
                Text("Sign up")
                    .foregroundColor(.white)
                    .fontWeight(.bold)
                    .padding(.vertical)
                    .frame(width: UIScreen.main.bounds.width - 50)
            }
            .background(Color.blue)
            .cornerRadius(6)
            .padding(.top, 10)
        }
        .padding(.horizontal, 25)
    }
    
    func register(){
        print("Register with username: \(observedObject.username)")
    }
}

struct SignUpScreen_Previews: PreviewProvider {
    static var previews: some View {
        SignUpScreen()
    }
}

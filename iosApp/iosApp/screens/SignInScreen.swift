//
//  SignInScreen.swift
//  iosApp
//
//  Created by Zhaolong Zhong on 4/23/22.
//

import SwiftUI
import shared

class SignInObservableObject: ObservableObject {
    @Published var username = ""
    @Published var password = ""
}

struct SignInScreen: View {
    @ObservedObject var observedObject = SignInObservableObject()
        
    var body: some View{
        VStack(){
            Text("App Name")
                .font(.title)
                .fontWeight(.bold)
                .padding(.bottom, 200)
            
            RectangleTextField(fieldHint: "Username or Email", nameOfField: $observedObject.username)
            
            PasswordField(fieldHint: "Password", nameOfField: $observedObject.password)
            
            HStack{
                Spacer()
                Button(action: {
                    self.resetPassword()
                }) {
                    Text("Forget Password")
                        .fontWeight(.medium)
                        .foregroundColor(Color.blue)
                }.padding(.top, 10.0)
            }
            
            Button(action: {
                self.signIn()
            }) {
                Text("Sign in")
                    .foregroundColor(.white)
                    .fontWeight(.bold)
                    .padding(.vertical)
                 .frame(width: UIScreen.main.bounds.width - 50)
            }
            .background(Color.blue)
            .cornerRadius(6)
            .padding(.top, 10)
            
            VStack{
                Text("Don't have an account ?")
                
                NavigationLink(destination: SignUpScreen()){
                    Text("Sign up")
                    .fontWeight(.bold)
                    .foregroundColor(Color.blue)
                    .padding(5)
                }
                
            }.padding(.top, 15)
        }
        .padding(.horizontal, 25)
    }
    
    func signIn() {
        print("Sign in with username: \(observedObject.username)")
    }
            
    func resetPassword() {
        print("Forget Password clicked")
    }
}

struct SignInScreen_Previews: PreviewProvider {
    static var previews: some View {
        SignInScreen()
    }
}

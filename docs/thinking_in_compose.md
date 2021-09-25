# Thinking in Compose

## [The declarative programming paradigm](https://developer.android.com/jetpack/compose/mental-model#paradigm)

### Imperative UI (traditional)

Manipulating views manually increases the likelihood of errors.

- If a piece of data is rendered in multiple places, it's easy to forget to update one of the views that shows it.
- It's also easy to create illegal states, when two updates conflict in an unexpected way.
- In general, the software maintenance complexity grows with the number of views that require updating.

### Declarative UI

Over the last serveral years, the entire industry has started shifting to a delarative UI model, which greatly simplifies the engineering associated with building and updating user interfaces.

The technique works by conceptually regenerating the entire scren from scratch, then applying only the necessary changes.

This approach avoid the complexity of manually updating a stateful view hierarchy.

Compose is a declarative UI framework.

## [Recomposition](https://developer.android.com/jetpack/compose/mental-model#recomposition)

Recomposition is the process of calling your composable function again when inputs change.

When Compose recomposess based on new inputs, it only call the functions or lambdas that might have changed, and skips the rest. By skipping all functions or lambdas that don't have changed parameters, Compose can recompose efficiently.

A number of things to be aware of when you program in Compose:

- Composable funtions can execute in any order.
- Composable functions can execute in paralle.
- Recomposition skips as many composable functions and lambdas as possible.
- Recomposition is optimistic and may be canceled.
- A composable function might be run quite frequently, as often as every frame of an animation.

## [Composesable functions can run in paralle](https://developer.android.com/jetpack/compose/mental-model#parallel)

If compose function writes to a local variable, this code will not be thread-safe or correct:

```kotlin
@Composable
@Deprecated("Example with bug")
fun ListWithBug(myList: List<String>) {
    var items = 0

    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            for (item in myList) {
                Text("Item: $item")
                items++ // Avoid! Side-effect of the column recomposing.
            }
        }
        Text("Count: $items")
    }
}
```

In this example, items is modified with every recomposition. That could be every frame of an animation, or when the list updates. Either way, the UI will display the wrong count. Because of this, writes like this are not supported in Compose; by prohibiting those writes, we allow the framework to change threads to execute composable lambdas.

## [Recomposition skips as much as possible](https://developer.android.com/jetpack/compose/mental-model#skips)

When portions of your UI are invalid, Compose does its best to recompose just the portion that need to be updated.

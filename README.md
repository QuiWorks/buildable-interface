# buildable-interface
An interface that provides a fluent API for constructing Java objects.

Example usage:
```
Pojo pojo = new Pojo().build(p -> p
                      .set(p::name, "joe")
                      .set(p::age, 1));
```
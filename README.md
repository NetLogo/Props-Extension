# NetLogo Props Extension

This extension allows you to read and write Java system properties from within NetLogo.

## Building

Run `sbt package`.  If compilation succeeds, `props.jar` will be created.

## Using

The Props extension bears only a small collection of primitives—after all, its purpose is to interact with Java system properties, and there's not a whole of primitives that you need in order to do that.  You can find explanation of the primitives below:

* `get <x>`
  * Returns the value for the Java system property with the name given by `<x>`.  Returns `false` if no such property is found.
* `set <x> <y>`
  * Sets the value of the Java system property with the name given by `<x>` to be the value given by `<y>`.

## Terms of Use

[![CC0](http://i.creativecommons.org/p/zero/1.0/88x31.png)](http://creativecommons.org/publicdomain/zero/1.0/)

The NetLogo Props extension is in the public domain.  To the extent possible under law, Uri Wilensky has waived all copyright and related or neighboring rights.

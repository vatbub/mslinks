mslinks
=======
Library for parsing and creating Windows shortcut files (.lnk)
***
This is a exact fork of [BlackOverlord666/mslinks](https://github.com/BlackOverlord666/mslinks), however, I published it on maven central:

**Java 10**
```xml
<dependency>
	<groupId>com.github.vatbub</groupId>
	<artifactId>mslinks</artifactId>
	<version>1.0.6.1</version>
</dependency>
```

**Java 1.8**
```xml
<dependency>
	<groupId>com.github.vatbub</groupId>
	<artifactId>mslinks</artifactId>
	<version>1.0.5</version>
</dependency>
```

**PLEASE NOTE** that this fork is not under active developement anymore. Please post any feature requests and bugs at [BlackOverlord666/mslinks](https://github.com/BlackOverlord666/mslinks).
***

Partial implementation of [Shell Link (.LNK) Binary File Format](http://msdn.microsoft.com/en-us/library/dd871305.aspx)

You can edit most properties of the link such as working directory, tooltip text, icon, command line arguments, hotkeys, create links to LAN shared files and directories but followed features are not implemented:

* extra data blocks: Darwin, IconEnvironment, KnownFolder, PropertyStore, Shim, SpecialFolder
* most options in LinkTargetIDList because it not documented, only key parts for resolving links are implemented, others are zero stub
* you can use environment variables in target path but they are resolved at creation time and not stored in the lnk file

Easiest way to create link with default parameters: `ShellLink.createLink("targetfile", "linkfile.lnk")`

Next sample demonstrates creating link for .bat file with setting working directory, icon and tune font parameters for console
```java
package mslinks;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		ShellLink sl = ShellLink.createLink("pause.bat")
			.setWorkingDir("..")
			.setIconLocation("%SystemRoot%\\system32\\SHELL32.dll");
		sl.getHeader().setIconIndex(128);
		sl.getConsoleData()
			.setFont(mslinks.extra.ConsoleData.Font.Consolas)
			.setFontSize(24)
			.setTextColor(5);
				
		sl.saveTo("testlink.lnk");
		System.out.println(sl.getWorkingDir());
		System.out.println(sl.resolveTarget());
	}
}

```

Final example creates recursive link that blocks explorer on Windows 7 while trying to get into the containing directory :D
```java
package mslinks;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		ShellLink sl = ShellLink.createLink("test.lnk");
		sl.getHeader().getLinkFlags().setAllowLinkToLink();
		sl.saveTo("test.lnk");
	}
}
```

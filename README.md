# capacitor-filechooser

A Capacitor plugin for file chooser

## Install

```bash
npm install capacitor-filechooser
npx cap sync
```

## Example

```typescript
import { FileChooser } from 'capacitor-filechooser'


// Add exclution or white list
constructor(private platfrom: Platform) {
    this.platfrom.ready().then(() => {
      // All mime type
    //   this.chooseFile();

      // mime type .pdf
      this.chooseFile("application/pdf");

      // mime type .txt
    //   this.chooseFile("text/plain");

      // mime type .png
    //   this.chooseFile("image/png");

      // mime type .jpeg
    //   this.chooseFile("image/jpeg");

      // mime type .wav
    //   this.chooseFile("audio/wav");

    }
    )
  }

chooseFile = async (mime?: string) => {
    const result = await FileChooser.open({mime});
  
    console.log('Result: ');
    console.log('File: '+result.file);
    console.log('Path: '+result.path);
    console.log('Message: '+result.messages);
  };
```

## API

<docgen-index>

* [`open(...)`](#open)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### open(...)

```typescript
open(filter: { mime?: string; }) => Promise<ChooseFileResult>
```

| Param        | Type                            |
| ------------ | ------------------------------- |
| **`filter`** | <code>{ mime?: string; }</code> |

**Returns:** <code>Promise&lt;<a href="#choosefileresult">ChooseFileResult</a>&gt;</code>

--------------------


### Interfaces


#### ChooseFileResult

| Prop           | Type                |
| -------------- | ------------------- |
| **`file`**     | <code>File</code>   |
| **`path`**     | <code>string</code> |
| **`messages`** | <code>string</code> |

</docgen-api>

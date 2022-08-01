# capacitor-filechooser

A Capacitor plugin for file chooser

## Install

```bash
npm install capacitor-filechooser
npx cap sync
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

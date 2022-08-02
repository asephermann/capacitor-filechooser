import { WebPlugin } from '@capacitor/core';

import type { ChooseFileResult, FileChooserPlugin } from './definitions';

export class FileChooserWeb extends WebPlugin implements FileChooserPlugin {
  async open(filter: { mime_types?: Array<string>; }): Promise<ChooseFileResult> {
    throw new Error('Method not implemented.'+filter);
  }
}
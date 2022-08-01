import { WebPlugin } from '@capacitor/core';

import type { ChooseFileResult, FileChooserPlugin } from './definitions';

export class FileChooserWeb extends WebPlugin implements FileChooserPlugin {
  async open(filter: { mime: string; }): Promise<ChooseFileResult> {
    throw new Error('Method not implemented.'+filter);
  }
}
export interface FileChooserPlugin {
  open(filter: { mime_types?: Array<string> }): Promise<ChooseFileResult>;
}

export interface ChooseFileResult {
  file?: File;
  path: string;
  messages?: string;
}
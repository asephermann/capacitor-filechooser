export interface FileChooserPlugin {
  open(filter: { mime?: string }): Promise<ChooseFileResult>;
}

export interface ChooseFileResult {
  file?: File;
  path: string;
  messages?: string;
}
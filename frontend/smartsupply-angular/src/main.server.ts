import { App } from './app/app';
import { config } from './app/app.config.server';
import { bootstrapApplication, type BootstrapContext } from '@angular/platform-browser';

export default function bootstrap(context: BootstrapContext) {
  return bootstrapApplication(App, config, context);
}

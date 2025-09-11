// main.ts
import { bootstrapApplication } from '@angular/platform-browser';
import { App } from './app/app';
import { appConfig } from './app/app.config';

// Bootstrap the Angular app
bootstrapApplication(App, appConfig)
  .then(() => {
    // بعد ما التطبيق يشتغل، ممكن نستخدم toast
  })
  .catch((err) => console.error(err));

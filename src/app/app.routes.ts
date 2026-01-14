import { Routes } from '@angular/router';
import { BlankComponent } from './layouts/blank/blank.component';
import { FullComponent } from './layouts/full/full.component';
import { ListeJobComponent } from './components/jobOffer/liste-job/liste-job.component';
import {InterventionListComponent} from "./components/intervention-list/intervention-list.component";
import {InterventionStatusComponent} from "./components/intervention-status/intervention-status.component";
import {InterventionAlertesComponent} from "./components/intervention-alerte/intervention-alerte.component";
import {AlerteListComponent} from "./components/alerte-list/alerte-list.component";
import {TechnicienListComponent} from "./components/technicien-list/technicien-list.component";
import {MesureSourceComponent} from "./components/mesure-source/mesure-source.component";

export const routes: Routes = [
  {
    path: '',
    component: FullComponent,
    children: [
      {
        path: 'job-offres',
        component: ListeJobComponent,
      },

      {
        path: 'apps/alertes',
        children: [
          { path: 'list', component: AlerteListComponent },
          { path: '', redirectTo: 'list', pathMatch: 'full' },
        ]
      },

      // 🛠 INTERVENTIONS (MAINTENANCE)
      {
        path: 'apps/interventions',
        children: [
          { path: 'list', component: InterventionListComponent },
          { path: 'status', component: InterventionStatusComponent },
          { path: 'alertes', component: InterventionAlertesComponent },
          { path: '', redirectTo: 'list', pathMatch: 'full' },
        ]
      },

      // 👷 TECHNICIENS
      {
        path: 'apps/techniciens',
        children: [
          { path: 'list', component: TechnicienListComponent },
          { path: '', redirectTo: 'list', pathMatch: 'full' },
        ]
      },

      // 📊 MESURES
      {
        path: 'apps/mesures',
        children: [
          { path: 'source', component: MesureSourceComponent },
          { path: '', redirectTo: 'source', pathMatch: 'full' },
        ]
      },
      {
        path: '',
        redirectTo: '/dashboards/dashboard1',
        pathMatch: 'full',
      },
      {
        path: 'starter',
        loadChildren: () =>
          import('./pages/pages.routes').then((m) => m.PagesRoutes),
      },
      {
        path: 'dashboards',
        loadChildren: () =>
          import('./pages/dashboards/dashboards.routes').then(
            (m) => m.DashboardsRoutes
          ),
      },

      {
        path: 'forms',
        loadChildren: () =>
          import('./pages/forms/forms.routes').then((m) => m.FormsRoutes),
      },
      {
        path: 'charts',
        loadChildren: () =>
          import('./pages/charts/charts.routes').then((m) => m.ChartsRoutes),
      },
      {
        path: 'apps',
        loadChildren: () =>
          import('./pages/apps/apps.routes').then((m) => m.AppsRoutes),
      },
      {
        path: 'widgets',
        loadChildren: () =>
          import('./pages/widgets/widgets.routes').then((m) => m.WidgetsRoutes),
      },
      {
        path: 'tables',
        loadChildren: () =>
          import('./pages/tables/tables.routes').then((m) => m.TablesRoutes),
      },
      {
        path: 'datatable',
        loadChildren: () =>
          import('./pages/datatable/datatable.routes').then(
            (m) => m.DatatablesRoutes
          ),
      },
      {
        path: 'theme-pages',
        loadChildren: () =>
          import('./pages/theme-pages/theme-pages.routes').then(
            (m) => m.ThemePagesRoutes
          ),
      },
      {
        path: 'ui-components',
        loadChildren: () =>
          import('./pages/ui-components/ui-components.routes').then(
            (m) => m.UiComponentsRoutes
          ),
      },
    ],
  },
  {
    path: '',
    component: BlankComponent,
    children: [
      {
        path: 'authentication',
        loadChildren: () =>
          import('./pages/authentication/authentication.routes').then(
            (m) => m.AuthenticationRoutes
          ),
      },
      {
        path: 'landingpage',
        loadChildren: () =>
          import('./pages/theme-pages/landingpage/landingpage.routes').then(
            (m) => m.LandingPageRoutes
          ),
      },
    ],
  },
  {
    path: '**',
    redirectTo: 'authentication/error',
  },
];

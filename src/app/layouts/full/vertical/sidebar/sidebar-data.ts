import { NavItem } from './nav-item/nav-item';

export const navItems: NavItem[] = [

  { navCap: 'Dashboard' },

  {
    displayName: 'Tableau de bord',
    iconName: 'layout-dashboard',
    bgcolor: 'primary',
    route: '/dashboard',
  },

  { navCap: 'Maintenance' },

  {
    displayName: 'Interventions',
    iconName: 'tool',
    bgcolor: 'warning',
    route: '/apps/interventions',
    children: [
      {
        displayName: 'Toutes les interventions',
        iconName: 'point',
        route: '/apps/interventions/list',
      },
      {
        displayName: 'Par statut',
        iconName: 'point',
        route: '/apps/interventions/status',
      },
      {
        displayName: 'Alertes liées',
        iconName: 'point',
        route: '/apps/interventions/alertes',
      },
    ],
  },

  {
    displayName: 'Techniciens',
    iconName: 'users',
    bgcolor: 'info',
    route: '/apps/techniciens',
    children: [
      {
        displayName: 'Liste des techniciens',
        iconName: 'point',
        route: '/apps/techniciens/list',
      },
      {
        displayName: 'Techniciens disponibles',
        iconName: 'point',
        route: '/apps/techniciens/disponibles',
      },
    ],
  },

  { navCap: 'Surveillance' },

  {
    displayName: 'Alertes',
    iconName: 'alert-triangle',
    bgcolor: 'error',
    route: '/apps/alertes',
    children: [
      {
        displayName: 'Toutes les alertes',
        iconName: 'point',
        route: '/apps/alertes/list',
      },
      {
        displayName: 'Alertes récentes',
        iconName: 'point',
        route: '/apps/alertes/recentes',
      },
      {
        displayName: 'Par gravité',
        iconName: 'point',
        route: '/apps/alertes/gravite',
      },
    ],
  },

  {
    displayName: 'Mesures & Analyses',
    iconName: 'activity',
    bgcolor: 'success',
    route: '/apps/mesures',
    children: [
      {
        displayName: 'Par source',
        iconName: 'point',
        route: '/apps/mesures/source',
      },
      {
        displayName: 'Par indicateur',
        iconName: 'point',
        route: '/apps/mesures/indicateur',
      },
    ],
  },
];

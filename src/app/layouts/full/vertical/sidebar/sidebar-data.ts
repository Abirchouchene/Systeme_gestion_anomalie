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
    
    ],
  },
];

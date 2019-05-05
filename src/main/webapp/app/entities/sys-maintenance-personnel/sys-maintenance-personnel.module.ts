import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysMaintenancePersonnelComponent,
    SysMaintenancePersonnelDetailComponent,
    SysMaintenancePersonnelUpdateComponent,
    SysMaintenancePersonnelDeletePopupComponent,
    SysMaintenancePersonnelDeleteDialogComponent,
    sysMaintenancePersonnelRoute,
    sysMaintenancePersonnelPopupRoute
} from './';

const ENTITY_STATES = [...sysMaintenancePersonnelRoute, ...sysMaintenancePersonnelPopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysMaintenancePersonnelComponent,
        SysMaintenancePersonnelDetailComponent,
        SysMaintenancePersonnelUpdateComponent,
        SysMaintenancePersonnelDeleteDialogComponent,
        SysMaintenancePersonnelDeletePopupComponent
    ],
    entryComponents: [
        SysMaintenancePersonnelComponent,
        SysMaintenancePersonnelUpdateComponent,
        SysMaintenancePersonnelDeleteDialogComponent,
        SysMaintenancePersonnelDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysMaintenancePersonnelModule {}

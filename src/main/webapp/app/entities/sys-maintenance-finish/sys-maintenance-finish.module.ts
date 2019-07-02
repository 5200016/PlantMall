import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysMaintenanceFinishComponent,
    SysMaintenanceFinishDetailComponent,
    SysMaintenanceFinishUpdateComponent,
    SysMaintenanceFinishDeletePopupComponent,
    SysMaintenanceFinishDeleteDialogComponent,
    sysMaintenanceFinishRoute,
    sysMaintenanceFinishPopupRoute
} from './';

const ENTITY_STATES = [...sysMaintenanceFinishRoute, ...sysMaintenanceFinishPopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysMaintenanceFinishComponent,
        SysMaintenanceFinishDetailComponent,
        SysMaintenanceFinishUpdateComponent,
        SysMaintenanceFinishDeleteDialogComponent,
        SysMaintenanceFinishDeletePopupComponent
    ],
    entryComponents: [
        SysMaintenanceFinishComponent,
        SysMaintenanceFinishUpdateComponent,
        SysMaintenanceFinishDeleteDialogComponent,
        SysMaintenanceFinishDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysMaintenanceFinishModule {}

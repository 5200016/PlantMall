import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysPlantLogComponent,
    SysPlantLogDetailComponent,
    SysPlantLogUpdateComponent,
    SysPlantLogDeletePopupComponent,
    SysPlantLogDeleteDialogComponent,
    sysPlantLogRoute,
    sysPlantLogPopupRoute
} from './';

const ENTITY_STATES = [...sysPlantLogRoute, ...sysPlantLogPopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysPlantLogComponent,
        SysPlantLogDetailComponent,
        SysPlantLogUpdateComponent,
        SysPlantLogDeleteDialogComponent,
        SysPlantLogDeletePopupComponent
    ],
    entryComponents: [SysPlantLogComponent, SysPlantLogUpdateComponent, SysPlantLogDeleteDialogComponent, SysPlantLogDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysPlantLogModule {}

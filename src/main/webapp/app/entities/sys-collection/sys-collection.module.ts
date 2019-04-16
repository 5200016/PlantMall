import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysCollectionComponent,
    SysCollectionDetailComponent,
    SysCollectionUpdateComponent,
    SysCollectionDeletePopupComponent,
    SysCollectionDeleteDialogComponent,
    sysCollectionRoute,
    sysCollectionPopupRoute
} from './';

const ENTITY_STATES = [...sysCollectionRoute, ...sysCollectionPopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysCollectionComponent,
        SysCollectionDetailComponent,
        SysCollectionUpdateComponent,
        SysCollectionDeleteDialogComponent,
        SysCollectionDeletePopupComponent
    ],
    entryComponents: [
        SysCollectionComponent,
        SysCollectionUpdateComponent,
        SysCollectionDeleteDialogComponent,
        SysCollectionDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysCollectionModule {}

/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlantMallTestModule } from '../../../test.module';
import { SysMaintenanceFinishDeleteDialogComponent } from 'app/entities/sys-maintenance-finish/sys-maintenance-finish-delete-dialog.component';
import { SysMaintenanceFinishService } from 'app/entities/sys-maintenance-finish/sys-maintenance-finish.service';

describe('Component Tests', () => {
    describe('SysMaintenanceFinish Management Delete Component', () => {
        let comp: SysMaintenanceFinishDeleteDialogComponent;
        let fixture: ComponentFixture<SysMaintenanceFinishDeleteDialogComponent>;
        let service: SysMaintenanceFinishService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysMaintenanceFinishDeleteDialogComponent]
            })
                .overrideTemplate(SysMaintenanceFinishDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysMaintenanceFinishDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysMaintenanceFinishService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});

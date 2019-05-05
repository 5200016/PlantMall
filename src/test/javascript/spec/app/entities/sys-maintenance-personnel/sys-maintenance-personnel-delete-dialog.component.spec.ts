/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlantMallTestModule } from '../../../test.module';
import { SysMaintenancePersonnelDeleteDialogComponent } from 'app/entities/sys-maintenance-personnel/sys-maintenance-personnel-delete-dialog.component';
import { SysMaintenancePersonnelService } from 'app/entities/sys-maintenance-personnel/sys-maintenance-personnel.service';

describe('Component Tests', () => {
    describe('SysMaintenancePersonnel Management Delete Component', () => {
        let comp: SysMaintenancePersonnelDeleteDialogComponent;
        let fixture: ComponentFixture<SysMaintenancePersonnelDeleteDialogComponent>;
        let service: SysMaintenancePersonnelService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysMaintenancePersonnelDeleteDialogComponent]
            })
                .overrideTemplate(SysMaintenancePersonnelDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysMaintenancePersonnelDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysMaintenancePersonnelService);
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

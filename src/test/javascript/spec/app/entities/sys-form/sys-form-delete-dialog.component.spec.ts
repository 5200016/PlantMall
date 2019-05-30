/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlantMallTestModule } from '../../../test.module';
import { SysFormDeleteDialogComponent } from 'app/entities/sys-form/sys-form-delete-dialog.component';
import { SysFormService } from 'app/entities/sys-form/sys-form.service';

describe('Component Tests', () => {
    describe('SysForm Management Delete Component', () => {
        let comp: SysFormDeleteDialogComponent;
        let fixture: ComponentFixture<SysFormDeleteDialogComponent>;
        let service: SysFormService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysFormDeleteDialogComponent]
            })
                .overrideTemplate(SysFormDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysFormDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysFormService);
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
